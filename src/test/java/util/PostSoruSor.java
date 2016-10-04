package util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by enbiya on 16.08.2016.
 */
public class PostSoruSor {


    public static void main(String[] args) {

        String msg = "Merhabalar,\n" +
                "\n" +
                "\n" +
                "internet üzerinde görüp aldığım aracı iade etmek istiyorum.\n" +
                "\n" +
                "Aracı görmeye gittiğimde sahibi sağ ön çamurluk haricinde araçta hiçbir kusur olmadığını söyledi. Hasar kaydı olarak bir adet kazası çıkıyor fakat fiyat yazmıyordu. Sigorta masraflarından dolayı aracı babamın üzerine aldık, ona da aynı şeyleri söyledi.\n" +
                "\n" +
                "Aracı aldıktan bir hafta sonra çekişinde sorun olduğunu fark ettik, sahibi kaçak benzinden dolayı olduğunu zamanla düzeleceğini söyledi. tüp sisteminin arızalı olduğu anladık ve 400 tl masraf yaptık. Parayı isteyince maddi durumunun olmadığını ve ödeyemeyeceğini söyledi.\n" +
                "\n" +
                "Klimasının arızalı olduğunu anladık, aracı kışın aldığını ve hiç kullanmadığı için arızalı olduğunu bilmiyordum diye cevap verdi.\n" +
                "\n" +
                "Şüpheler artınca expertize gösterdim, aracın komple boyalı olduğu, sağ sol ön çamurlukların değiştiği, sağ ön kapının değiştiği ön kaporta ve tavanda macun olduğu, komple boyalı olduğu ayrıca motor ömrünün bitik olduğu ortaya çıktı (motor elimizde kalır endişesiyle motor testine bile sokamadık aracı). \n" +
                "\n" +
                "Geri iade etmek istediğimizde aradan 40 gün geçmiş bu sürede motoru sende bitirirsin diyerek talebi reddetti. Noter üzerinden iade için tebligat gönderdim. Yaklaşık bir ay zaman geçti noter cevabı gelmedi. Benim nasıl bir yol izlemem gerekiyor yardımcı olur musunuz. (Aracım Trabzonda)\n" +
                "\n" +
                "\n" +
                "şimdiden teşekkür ederim.";

        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost("http://www.atamer.av.tr/iletisim/");
        httpPost.addHeader("Host", "www.atamer.av.tr");
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpPost.addHeader("Origin", "http://www.atamer.av.tr");
        httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.addHeader("Referer", "http://www.atamer.av.tr/iletisim/");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate");
        httpPost.addHeader("Accept-Language", "tr-TR,tr;q=0.8,en-US;q=0.6,en;q=0.4");
        httpPost.addHeader("Cookie", "_gat=1; _ym_uid=1471346672617038853; _ym_isad=2; _ga=GA1.3.278788000.1471346672; _ym_visorc_26828439=w" +
                "_wpcf7=599&_wpcf7_version=4.5&_wpcf7_locale=tr_TR&_wpcf7_unit_tag=wpcf7-f599-p18-o1&_wpnonce=b4f297ac67&" +
                "your-name=Enes Koç&" +
                "tel-798=boş&" +
                "your-email=eneskoc3488%40gmail.com&" +
                "your-subject=2.el ayıplı araç iadesi hk.&" +
                "your-message="+msg +
                "&_wpcf7_is_ajax_call=1");


        try {
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println(response.getEntity().getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
