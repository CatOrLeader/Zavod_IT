package innohackatons.zavod_it;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.junit.jupiter.api.Test;

public class IceTradeClientTest {
    @Test
    void testConnectionGet() {
        try {
            // Отключение проверки сертификата
            trustAllCertificates();

            // URL для запроса
            URL url = new URL(
                "https://icetrade.by/search/foreign_uno?search_text=&auc_num=&company_title=&countries%5B%5D=RUS&industries=&period=&created_from=&created_to=&request_end_from=&request_end_to=&sort=num:desc&sbm=1&onPage=20");

            // Открытие соединения
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // Установка метода запроса
            connection.setRequestMethod("GET");

            // Получение ответа
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Создание потока для чтения данных из соединения
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                // Чтение ответа строка за строкой
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Вывод ответа
                System.out.println(response);
            } else {
                // В случае ошибки выводим код ответа
                System.out.println("GET request not worked, response code: " + responseCode);
            }

            // Закрытие соединения
            connection.disconnect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void trustAllCertificates() throws Exception {
        // Создание доверенного менеджера
        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };

        // Получение контекста SSL
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());

        // Применение настроек к HTTPS соединению
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
}
