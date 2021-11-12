package com.library.basemodule.entity;

import java.util.List;

/**
 * <pre>
 *     author: 梁幸福
 *     time  : 2018/5/18
 *     desc  : app城市选择的java 实体类
 * </pre>
 */

public class AppCityEntity {


    /**
     * code : 440000
     * name : 广东省
     * districts : [{"code":"440500","name":"汕头市","districts":[{"code":"440523","name":"南澳县"},{"code":"440515","name":"澄海区"},{"code":"440512","name":"濠江区"},{"code":"440513","name":"潮阳区"},{"code":"440514","name":"潮南区"},{"code":"440507","name":"龙湖区"},{"code":"440511","name":"金平区"}]},{"code":"440600","name":"佛山市","districts":[{"code":"440607","name":"三水区"},{"code":"440608","name":"高明区"},{"code":"440606","name":"顺德区"},{"code":"440604","name":"禅城区"},{"code":"440605","name":"南海区"}]},{"code":"440400","name":"珠海市","districts":[{"code":"440403","name":"斗门区"},{"code":"440404","name":"金湾区"},{"code":"440402","name":"香洲区"},{"code":"440499","name":"澳门大学横琴校区(由澳门管辖)"}]},{"code":"440700","name":"江门市","districts":[{"code":"440784","name":"鹤山市"},{"code":"440704","name":"江海区"},{"code":"440783","name":"开平市"},{"code":"440781","name":"台山市"},{"code":"440785","name":"恩平市"},{"code":"440705","name":"新会区"},{"code":"440703","name":"蓬江区"}]},{"code":"440800","name":"湛江市","districts":[{"code":"440883","name":"吴川市"},{"code":"440881","name":"廉江市"},{"code":"440882","name":"雷州市"},{"code":"440825","name":"徐闻县"},{"code":"440803","name":"霞山区"},{"code":"440811","name":"麻章区"},{"code":"440804","name":"坡头区"},{"code":"440802","name":"赤坎区"},{"code":"440823","name":"遂溪县"}]},{"code":"441300","name":"惠州市","districts":[{"code":"441324","name":"龙门县"},{"code":"441322","name":"博罗县"},{"code":"441323","name":"惠东县"},{"code":"441303","name":"惠阳区"},{"code":"441302","name":"惠城区"}]},{"code":"441200","name":"肇庆市","districts":[{"code":"441226","name":"德庆县"},{"code":"441203","name":"鼎湖区"},{"code":"441202","name":"端州区"},{"code":"441225","name":"封开县"},{"code":"441204","name":"高要区"},{"code":"441223","name":"广宁县"},{"code":"441224","name":"怀集县"},{"code":"441284","name":"四会市"}]},{"code":"441500","name":"汕尾市","districts":[{"code":"441523","name":"陆河县"},{"code":"441581","name":"陆丰市"},{"code":"441521","name":"海丰县"},{"code":"441502","name":"城区"}]},{"code":"440900","name":"茂名市","districts":[{"code":"440983","name":"信宜市"},{"code":"440981","name":"高州市"},{"code":"440982","name":"化州市"},{"code":"440904","name":"电白区"},{"code":"440902","name":"茂南区"}]},{"code":"440300","name":"深圳市","districts":[{"code":"440306","name":"宝安区"},{"code":"440304","name":"福田区"},{"code":"440307","name":"龙岗区"},{"code":"440309","name":"龙华区"},{"code":"440303","name":"罗湖区"},{"code":"440305","name":"南山区"},{"code":"440310","name":"坪山区"},{"code":"440308","name":"盐田区"}]},{"code":"441700","name":"阳江市","districts":[{"code":"441781","name":"阳春市"},{"code":"441702","name":"江城区"},{"code":"441721","name":"阳西县"},{"code":"441704","name":"阳东区"}]},{"code":"445100","name":"潮州市","districts":[{"code":"445122","name":"饶平县"},{"code":"445102","name":"湘桥区"},{"code":"445103","name":"潮安区"}]},{"code":"440200","name":"韶关市","districts":[{"code":"440224","name":"仁化县"},{"code":"440205","name":"曲江区"},{"code":"440282","name":"南雄市"},{"code":"440232","name":"乳源瑶族自治县"},{"code":"440204","name":"浈江区"},{"code":"440203","name":"武江区"},{"code":"440222","name":"始兴县"},{"code":"440229","name":"翁源县"},{"code":"440233","name":"新丰县"},{"code":"440281","name":"乐昌市"}]},{"code":"441800","name":"清远市","districts":[{"code":"441826","name":"连南瑶族自治县"},{"code":"441882","name":"连州市"},{"code":"441825","name":"连山壮族瑶族自治县"},{"code":"441881","name":"英德市"},{"code":"441821","name":"佛冈县"},{"code":"441823","name":"阳山县"},{"code":"441803","name":"清新区"},{"code":"441802","name":"清城区"}]},{"code":"441400","name":"梅州市","districts":[{"code":"441426","name":"平远县"},{"code":"441427","name":"蕉岭县"},{"code":"441481","name":"兴宁市"},{"code":"441424","name":"五华县"},{"code":"441403","name":"梅县区"},{"code":"441423","name":"丰顺县"},{"code":"441422","name":"大埔县"},{"code":"441402","name":"梅江区"}]},{"code":"445300","name":"云浮市","districts":[{"code":"445322","name":"郁南县"},{"code":"445381","name":"罗定市"},{"code":"445321","name":"新兴县"},{"code":"445303","name":"云安区"},{"code":"445302","name":"云城区"}]},{"code":"441600","name":"河源市","districts":[{"code":"441624","name":"和平县"},{"code":"441622","name":"龙川县"},{"code":"441623","name":"连平县"},{"code":"441625","name":"东源县"},{"code":"441602","name":"源城区"},{"code":"441621","name":"紫金县"}]},{"code":"441900","name":"东莞市","districts":[{"code":"441900","name":"常平镇"},{"code":"441900","name":"望牛墩镇"},{"code":"441900","name":"莞城街道"},{"code":"441900","name":"大朗镇"},{"code":"441900","name":"麻涌镇"},{"code":"441900","name":"黄江镇"},{"code":"441900","name":"东莞生态园"},{"code":"441900","name":"凤岗镇"},{"code":"441900","name":"樟木头镇"},{"code":"441900","name":"桥头镇"},{"code":"441900","name":"松山湖管委会"},{"code":"441900","name":"石龙镇"},{"code":"441900","name":"高埗镇"},{"code":"441900","name":"寮步镇"},{"code":"441900","name":"塘厦镇"},{"code":"441900","name":"厚街镇"},{"code":"441900","name":"谢岗镇"},{"code":"441900","name":"虎门镇"},{"code":"441900","name":"南城街道"},{"code":"441900","name":"虎门港管委会"},{"code":"441900","name":"横沥镇"},{"code":"441900","name":"企石镇"},{"code":"441900","name":"东坑镇"},{"code":"441900","name":"东城街道"},{"code":"441900","name":"石排镇"},{"code":"441900","name":"洪梅镇"},{"code":"441900","name":"沙田镇"},{"code":"441900","name":"长安镇"},{"code":"441900","name":"道滘镇"},{"code":"441900","name":"大岭山镇"},{"code":"441900","name":"清溪镇"},{"code":"441900","name":"茶山镇"},{"code":"441900","name":"石碣镇"},{"code":"441900","name":"中堂镇"},{"code":"441900","name":"万江街道"}]},{"code":"440100","name":"广州市","districts":[{"code":"440117","name":"从化区"},{"code":"440118","name":"增城区"},{"code":"440114","name":"花都区"},{"code":"440115","name":"南沙区"},{"code":"440112","name":"黄埔区"},{"code":"440111","name":"白云区"},{"code":"440106","name":"天河区"},{"code":"440105","name":"海珠区"},{"code":"440113","name":"番禺区"},{"code":"440103","name":"荔湾区"},{"code":"440104","name":"越秀区"}]},{"code":"445200","name":"揭阳市","districts":[{"code":"445222","name":"揭西县"},{"code":"445281","name":"普宁市"},{"code":"445224","name":"惠来县"},{"code":"445203","name":"揭东区"},{"code":"445202","name":"榕城区"}]},{"code":"442000","name":"中山市","districts":[{"code":"442000","name":"三角镇"},{"code":"442000","name":"横栏镇"},{"code":"442000","name":"五桂山街道"},{"code":"442000","name":"东升镇"},{"code":"442000","name":"神湾镇"},{"code":"442000","name":"火炬开发区街道"},{"code":"442000","name":"小榄镇"},{"code":"442000","name":"南朗镇"},{"code":"442000","name":"古镇镇"},{"code":"442000","name":"民众镇"},{"code":"442000","name":"港口镇"},{"code":"442000","name":"三乡镇"},{"code":"442000","name":"石岐区街道"},{"code":"442000","name":"大涌镇"},{"code":"442000","name":"南头镇"},{"code":"442000","name":"黄圃镇"},{"code":"442000","name":"东区街道"},{"code":"442000","name":"阜沙镇"},{"code":"442000","name":"板芙镇"},{"code":"442000","name":"西区街道"},{"code":"442000","name":"坦洲镇"},{"code":"442000","name":"南区街道"},{"code":"442000","name":"沙溪镇"},{"code":"442000","name":"东凤镇"}]},{"code":"442100","name":"东沙群岛","level":"city"}]
     */

    private String code;
    private String name;
    private List<CityDistrictsEntity> districts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityDistrictsEntity> getDistricts() {
        return districts;
    }

    public void setDistricts(List<CityDistrictsEntity> districts) {
        this.districts = districts;
    }

    public static class CityDistrictsEntity {
        /**
         * code : 440500
         * name : 汕头市
         * districts : [{"code":"440523","name":"南澳县"},{"code":"440515","name":"澄海区"},{"code":"440512","name":"濠江区"},{"code":"440513","name":"潮阳区"},{"code":"440514","name":"潮南区"},{"code":"440507","name":"龙湖区"},{"code":"440511","name":"金平区"}]
         * level : city
         */

        private String code;
        private String name;
        private String level;
        private List<DistrictsEntity> districts;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public List<DistrictsEntity> getDistricts() {
            return districts;
        }

        public void setDistricts(List<DistrictsEntity> districts) {
            this.districts = districts;
        }

        public static class DistrictsEntity {
            /**
             * code : 440523
             * name : 南澳县
             */

            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
