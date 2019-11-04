package com.estetiCloud.oauth;

public class JwtConfig {

    //public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";

    public static final String RSA_PRIVADA ="-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAsnI6L0As7vmyMsD80BYWbJFPIXrqxGgJSYJHKfk/isT4sENV\n" +
            "3FYSm3S825un3asVCz839PeznNhn/t8Qpcuz4igYxaK318b/RkT191xAIafdAmjg\n" +
            "1RidkbsIKfRmVuyYgCK2dERj+H8B+erFB8SGfhaZmwEsj5esgao/IVxJgLzXBdiq\n" +
            "Zg7wErPt4E2Ej+nxIhGxNn9pfZqL7NZ2JsTBqpzf7GSUuluqyu3ZPNxcrMbK+5EF\n" +
            "CLlrpV1eD6tKL8xAJageOFmm7xILct+2BFUlMad6jQ1NLytzLf0KR8sBZuS3cE53\n" +
            "9EAMTAUFKe4Gu6lZ5A/NUCO+sGf3wEt2VNZ77wIDAQABAoIBAQCjXf40yf0ZH9Jr\n" +
            "stEBNGd9J58ZWK3b+B8ghDvgiocI5YGD5XGS0d4hIkPQjfWl8/3FGTCCfc+bNWgo\n" +
            "J1HXZ+Gcl/bt4SWMT2vfijzhJDy2Uzb8AMZtcSa4h1FXD6AtzldjX+RIF2Cq3KQT\n" +
            "iEKv9NToVIp2JSIFp+dPomw/f3zvdOtP7KPswkg8R+21sVlrjtpRbMsifOjf/pz0\n" +
            "lbhe1tQD1AX9PxHTszClH3kDdV5xETfY+C5xz3MEGrSSw/3SlPLXPyyqR+F6hNLH\n" +
            "KrDPjkDwU2TOs2s49WY1VQd2z4+UkZV9WTpmImjnbch8T26bmuzQ09LfGAKQZhip\n" +
            "CH1Bfa2ZAoGBAODSNx22EvbH2jR7B6QetAumNSYbPhqoD30yf2H2fensZDyiC5TC\n" +
            "JjWN1Gz2SzHbGcFHsAIcFVEjji46piSbuDF6+VaInWHwm6zQM3pqzU08PRygUaDK\n" +
            "HVZdUTgPi9YsU7suvU6FvtUObWVLGW2g2er15daFsjaESmZJoy8CnSKzAoGBAMsx\n" +
            "kgceqZ4EE9DdQNziWJ+5yu+6TUU0XF8AXxrTQtDJFHYBojd/dK9ubkrD1ju+SyjH\n" +
            "4ITcAE75CB1y8UUSsvP8aXrkS4fQTJwegjJvCJ2rFDd0lt5t3hgPESK/HSyRZvzV\n" +
            "GWd0p2g9kWmY3WaT7qJZjysGSPTqu8QLjZSUP2/VAoGBAJzHO5RGkjbx62LJoOx8\n" +
            "VMYC4/Fs8gQYyqReAKLKB8iHBueZgUX9ljOmckqB/rIHvpAgc8ck2mC40L/AOngw\n" +
            "92PaA+KqZkYUShtbHWry67Q+m8dIrWPZTlgZvze+758tTj6sO2t0rT1aUENg+ZyP\n" +
            "nSfSzrKojTyjSFbIZzmzMu0tAoGAVPbpZOjBbD2wO4oI8VQEUuXRoeR5IxL9/Que\n" +
            "uQzVx5ANajrMpgA5vpGitMim8Fxl+St7CpMj3eHsLsQ9dvCWu8c0JBsKKkRDhJSN\n" +
            "RKGBLMy4q2epHmE6mTzToX/gpx41vkstUEFLLx9vioRNL/NzuZdRcmAyaZct5JAg\n" +
            "J5zF2fECgYBZoYz1GwgcAVNO5TUrpKVKYPdEkR32rn0TmLV4egHjqK1+LLOT4J01\n" +
            "KrlIpGROf6kGQTss8p6N/HrKjKZwc7Jp/YO2HlqooEFGAkWcZwlxUMrTF12enZGG\n" +
            "SDaQz0z3dRH7D199N38p+HcUwDZUsJIRaLRf7HoaHha3uv5mR3/Ayw==\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLICA ="-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsnI6L0As7vmyMsD80BYW\n" +
            "bJFPIXrqxGgJSYJHKfk/isT4sENV3FYSm3S825un3asVCz839PeznNhn/t8Qpcuz\n" +
            "4igYxaK318b/RkT191xAIafdAmjg1RidkbsIKfRmVuyYgCK2dERj+H8B+erFB8SG\n" +
            "fhaZmwEsj5esgao/IVxJgLzXBdiqZg7wErPt4E2Ej+nxIhGxNn9pfZqL7NZ2JsTB\n" +
            "qpzf7GSUuluqyu3ZPNxcrMbK+5EFCLlrpV1eD6tKL8xAJageOFmm7xILct+2BFUl\n" +
            "Mad6jQ1NLytzLf0KR8sBZuS3cE539EAMTAUFKe4Gu6lZ5A/NUCO+sGf3wEt2VNZ7\n" +
            "7wIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}
