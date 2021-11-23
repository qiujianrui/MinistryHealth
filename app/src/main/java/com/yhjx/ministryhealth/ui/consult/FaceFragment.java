package com.yhjx.ministryhealth.ui.consult;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.yhjx.ministryhealth.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FaceFragment extends Fragment {


    private ViewPager faceViewPager;
    private int columns = 8;
    private int rows = 4;
    private ArrayList<String> emojiList;
    ArrayList<View> ViewPagerItems = new ArrayList<>();
    private OnEmojiClickListener listener;
    public FaceFragment() {
        // Required empty public constructor
    }

    public static FaceFragment newInstance( ) {
        FaceFragment fragment = new FaceFragment();

        return fragment;
    }

    public void setListener(OnEmojiClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAttach(Activity activity) {
        if (activity instanceof OnEmojiClickListener) {
            this.listener = (OnEmojiClickListener) activity;
        }
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emojiList=new ArrayList<>();
        String[] strings=getResources().getStringArray(R.array.emoji);
        emojiList= new ArrayList<String>(Arrays.asList(strings));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_face, container, false);
        ViewGroup.LayoutParams params = view.getLayoutParams();
         params.height = 500;
        view.setLayoutParams(params);
        faceViewPager = view.findViewById(R.id.face_viewPager);
        initViews();
        return view;
    }

    private void initViews() {
        initViewPager(emojiList, columns, rows);
    }

    private void initViewPager(ArrayList<String> list, int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        ViewPagerItems.clear();
        int pageCont = getPagerCount(list);
        for (int i = 0; i < pageCont; i++) {
            ViewPagerItems.add(getViewPagerItem(i, list));
        }
        FaceVPAdapter mVpAdapter = new FaceVPAdapter(ViewPagerItems);
        faceViewPager.setAdapter(mVpAdapter);
    }

    /**
     * 根据表情数量以及GridView设置的行数和列数计算Pager数量
     *
     * @return
     */
    private int getPagerCount(List<String> list) {
        int count = list.size();
        int dit = 1;
        return count % (columns * rows - dit) == 0 ? count / (columns * rows - dit)
                : count / (columns * rows - dit) + 1;
    }

    private View getViewPagerItem(int position, ArrayList<String> list) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.layout_face_grid, null);//表情布局
        GridView gridview = layout.findViewById(R.id.chart_face_gv);
        /**
         * 注：因为每一页末尾都有一个删除图标，所以每一页的实际表情columns *　rows　－　1; 空出最后一个位置给删除图标
         * */
        final List<String> subList = new ArrayList<>();
        int dit = 1;
//        if (mCurrentGroupIndex > 0)
//            dit = 0;
        subList.addAll(list.subList(position * (columns * rows - dit),
                (columns * rows - dit) * (position + 1) > list
                        .size() ? list.size() : (columns
                        * rows - dit)
                        * (position + 1)));
        /**
         * 末尾添加删除图标
         * */
        if ( subList.size() < (columns * rows - dit)) {
            for (int i = subList.size(); i < (columns * rows - dit); i++) {
                subList.add(null);
            }
        }
            subList.add("del");
        FaceGVAdapter mGvAdapter = new FaceGVAdapter(subList, getActivity());
        gridview.setAdapter(mGvAdapter);
        gridview.setNumColumns(columns);
        // 单击表情执行的操作
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == columns * rows - 1) {
                    if (listener != null) {
                            listener.onEmojiDelete();
                        }
                        return;
                    }
                    if (listener != null) {
                        listener.onEmojiClick(subList.get(position));
                    }



                //insertToRecentList(subList.get(position));
            }
        });

        return gridview;
    }

    public interface OnEmojiClickListener {
        void onEmojiDelete();

        void onEmojiClick(String emoji);

    }

    class FaceGVAdapter extends BaseAdapter {
        private List<String> list;
        private Context mContext;

        public FaceGVAdapter(List<String> list, Context mContext) {
            super();
            this.list = list;
            this.mContext = mContext;
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            String emoji = list.get(position);
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_face, null);
                holder.iv = convertView.findViewById(R.id.face_tv);
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) holder.iv.getLayoutParams();
                if (position / columns == 0) {
                    params.setMargins(0, 10, 0, 0);
                } else if (rows == 2) {
                    params.setMargins(0, 10, 0, 0);
                } else {
                    if (position / columns < rows - 1) {
                        //左右间距
//                        params.setMargins(0, 10, 0, 10);
                    } else {
                        params.setMargins(0, 0, 0, 10);
                    }
                }

                holder.iv.setLayoutParams(params);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (emoji != null) {
                if (emoji.equals("del")){
                    holder.iv.setBackground(getResources().getDrawable(R.mipmap.face_delete));
                }else {
                    holder.iv.setText(emoji);
                }
            }
            return convertView;
        }

        class ViewHolder {
            TextView iv;
        }
    }

    class FaceVPAdapter extends PagerAdapter {
        // 界面列表
        private List<View> views;

        public FaceVPAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) (arg2));
        }

        @Override
        public int getCount() {
            return views.size();
        }

        // 初始化arg1位置的界面
        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1));
            return views.get(arg1);
        }

        // 判断是否由对象生成界
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }
    }
}