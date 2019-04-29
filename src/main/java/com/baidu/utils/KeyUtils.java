package com.baidu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;

import com.google.common.base.Splitter;

import sun.misc.BASE64Decoder;

public class KeyUtils {
    private static final String PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDPK29yszd6tM0l\n"
                                                      + "GNasUithHx65dyk7oyvfFQl90Kkj4joOOHU25D"
                                                      + "+3wDsIEfIbVznRr0eB3veKeNEk\n"
                                                      + "+iKn7pVP7Qu1FM5d4ZozosZsKYpZEqLl6IxtrNraKfIGuW0Inl7mH4YQAHCH4U"
                                                      + "/x\n" + "IZcou52l4Y+x477tFQlqA2sq5/0hjE3wF8q6MoNcOVA0p+xi"
                                                      + "/ZHHCdfJG9LEw7C/\n"
                                                      + "evG9lJscjPCqYhEXXnB6V+s7vD3nvyF6WmDw43qB4"
                                                      + "/1ofpkzCfxJ5mAgifktb81o\n"
                                                      + "h/FVCVR8syRa53FzKo2vvw9eG/2iZ2otlE1pVZX42r1rZ9JlW7eOzxK2c"
                                                      + "/oCRsmw\n"
                                                      +
                                                      "Eprqxie3AgMBAAECggEAM33HQIn80xnXF05aJRBzSbtzZoOQkwsWX2caKdRQBeQ3\n"
                                                      +
                                                      "q8sqjw8iKyizQ2k6dMM0UsKvEj955UfsK7WkIXsyT2BKlaJrPHhTXFwr1zxn74Wr\n"
                                                      +
                                                      "SVuIc8YPy0O7rSIvUIIUcjwJlTaWwZ8lwHgp2uzu3Qj4Ly29Ca493cSlwU9R4iFL\n"
                                                      + "ioWe2vBdhytV+mv1t5ytW96oxCLElv0koRW1o6s4suMWrJTTw/7Q9TUWZD"
                                                      + "+So9E1\n" + "j26jUtAz7md5iI5e2Ubb2XLcmUVJnckB4oEf4KuwIAkYf"
                                                      + "+D80BKMDvPh7uGLJ2sC\n" + "14e+zogQ7T3ZlK3emZfn"
                                                      + "/mbUPkMTCZeFNfCjef3SsQKBgQD2l7b2beiVHoKVk4Bf\n"
                                                      + "LcwSZehR9Ni6RipDSa0cY3gwPcFQ4MbYDRpqpATqWalZTScsloqABFEqTxxqd"
                                                      + "/xs\n"
                                                      + "ByuGJMZu9XkhNPfstlAbVFS6LoGM3GSR1nixTvngt4PHjKkGQDFs4xV"
                                                      + "+O1hOLlIn\n" + "zr7LGIUdQ1TNfJUDKePK+/zP3wKBgQDXErTDYO"
                                                      + "+ybswhz0hFOAwoEVcCrmjFhGcc\n"
                                                      + "TGsL0XIcmi6gf/vvNWGyEeM8lVklVR1tgEdXOl"
                                                      + "+yFaa2aQl4YOpfUgO7Gkhi3lqe\n" + "lgn4ZJKUntxy"
                                                      + "+GXZw8ZuY4F6nEsDWpfty97HF2QLRcWXou8MJZMbivFTThLpHvsU\n"
                                                      + "H4cJOU7DKQKBgQCvi9vdXOsJVVoDH6lwmIrBwjh8N8EeAptNoaKSJiHGvf"
                                                      + "/kEtCb\n" + "t+BJcJBrQP81Kp6ruFPKxMf"
                                                      + "/RahmBxr00LnkWkPFdA0SXcHtivBGNGfIzxi87ZzO\n"
                                                      + "0Y7EMwPiPOKcaX9eis5kxNhlL9wyc/lai2z56m"
                                                      + "/BIFC7pGf0g5q9w1v7owKBgQC0\n"
                                                      +
                                                      "ajy4qNhqFgenVPqLFjkcY29aEHRTPH5CfthNUaUn2S7D9JiiUWViux6j86SUbJID\n"
                                                      + "CVjPE2izOvZejj4jfSkeTO5URffy"
                                                      + "+BOMdNBoTfWoNps21qiZxGDwuQgkkndamORZ\n"
                                                      + "1mtLF5QU4LKgQU3GIr8Em8lU2GmEpTh56PieBDTOAQKBgQDgqzAipOC"
                                                      + "+aRR6VAWr\n"
                                                      + "teEkIeAU7SkSetULLAOTo4c5k0gDTlUkZMvLHaypcNDNYW2kmegma"
                                                      + "/q5GLGeiOqX\n"
                                                      +
                                                      "5rStQTtz5pnbosJ6DRw4slLG5lZoEVFZy7U1OjeVIyAB7Cpe2TTGTsz54Ep78bC2\n"
                                                      + "8LYHxeBf/NYuQe+uILETXUFPeQ==";

    public static PublicKey getPublicKey() throws CertificateException, IOException {
        File crtFile = new File("/Users/langshiquan/docker-workspace/certs/cert.crt");
        try (FileInputStream in = new FileInputStream(crtFile)) {
            PublicKey key = CertificateFactory.getInstance("X.509").generateCertificate(in).getPublicKey();
            return key;
        }

    }

    public static PrivateKey getPrivateKey() throws Exception {
        //获取KeyFactory，指定RSA算法
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //将BASE64编码的私钥字符串进行解码
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] encodeByte = decoder.decodeBuffer(PRIVATE_KEY);
        //将BASE64解码后的字节数组，构造成PKCS8EncodedKeySpec对象，生成私钥对象
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodeByte));
        return privateKey;
    }

    public static String getKeyId() throws CertificateException, IOException {
        byte[] payload = ArrayUtils.subarray(DigestUtils.sha256(getPublicKey().getEncoded()), 0, 30);
        String kid = String.join(":", Splitter.fixedLength(4).split(new Base32().encodeAsString(payload)));
        return kid;
    }
}
