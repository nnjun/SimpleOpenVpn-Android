package com.app.vpn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import de.blinkt.openvpn.BindUtils;
import de.blinkt.openvpn.BindStatus;
import de.blinkt.openvpn.base.StatusInfo;
import de.blinkt.openvpn.base.VPNActivity;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;


public class MainActivity extends VPNActivity {

    String data = "client\n" +
            "dev tun\n" +
            "proto tcp\n" +
            "sndbuf 0\n" +
            "rcvbuf 0\n" +
            "remote 39.108.145.77 1195\n" +
            "resolv-retry infinite\n" +
            "nobind\n" +
            "persist-key\n" +
            "persist-tun\n" +
            "remote-cert-tls server\n" +
            "auth SHA512\n" +
            "cipher AES-256-CBC\n" +
            "setenv opt block-outside-dns\n" +
            "key-direction 1\n" +
            "verb 3\n" +
            "auth-user-pass\n" +
            "<ca>\n" +
            "-----BEGIN CERTIFICATE-----\n" +
            "MIIDKzCCAhOgAwIBAgIJAOnzT4FmyNE5MA0GCSqGSIb3DQEBCwUAMBMxETAPBgNV\n" +
            "BAMMCENoYW5nZU1lMB4XDTE5MDgxMTA3MDQ1MFoXDTI5MDgwODA3MDQ1MFowEzER\n" +
            "MA8GA1UEAwwIQ2hhbmdlTWUwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIB\n" +
            "AQC5VLI22Qbx3LHc3bqbznKw2Mi0s0txVR3wS/mgrZ+5cdGWppqYXjElV6R6CQhg\n" +
            "9yQe+nYtsPpS3HPibLpjm0cw7r1K9vX+JYJ40Oh8+FV1wBmt8udyzVoA7Rq787tX\n" +
            "ZQrNWG7Lftcl/OSxeXcE3GL8fXre4IqaINLoDzBfESiRCyt7JkulI4rq9bP+4yfH\n" +
            "jOEyiaMIfC2HJSiQ03lJoZoxdnfE6ZaAOVWep0ZFp+PIW14cTsopaP3JD3CpFhzw\n" +
            "hlxbcGzFyZKHGEOavjZzlnNE5bT9q3fWbRDzkUG6geYsDqNMJPWphQfDpP3S9Lkr\n" +
            "eYfYzSjVIxv7OFCsx3N9brnTAgMBAAGjgYEwfzAdBgNVHQ4EFgQUFQ1PKbvAHjBD\n" +
            "4FF6yeY/7tjpIJkwQwYDVR0jBDwwOoAUFQ1PKbvAHjBD4FF6yeY/7tjpIJmhF6QV\n" +
            "MBMxETAPBgNVBAMMCENoYW5nZU1lggkA6fNPgWbI0TkwDAYDVR0TBAUwAwEB/zAL\n" +
            "BgNVHQ8EBAMCAQYwDQYJKoZIhvcNAQELBQADggEBAD0RPxlO4710IxCj5NhbT3C/\n" +
            "o4Yh0FF8rkBArgXCbLu+2SS79oNSaZhxST1gpLEe/50RysAB+WFZrjjSIqOsFcv+\n" +
            "T2+oEIPbVd29dsm5W3WZorxDF1H3DE5uU4HEdIiaSFf3DPcoMn0NrXRPl6II9Vfe\n" +
            "EPGFpp3iJn6feE9y4kWX2pT53jgBRXLWDSnpLqQdiswc+RdUwvluSbUz00Ti1+mp\n" +
            "qBGoW2p6vyUk/YCeUNovRoGIJH15v3Spt9P80HxFX787XnJ2iBwjwL6/PD7zAmsg\n" +
            "Wj1qr0xXJn7anf0aNfJQ4GIZucw3PQrIoCV/7h/BncE3I1MSeqdD2A8Qx5/6Kfo=\n" +
            "-----END CERTIFICATE-----\n" +
            "</ca>\n" +
            "<cert>\n" +
            "-----BEGIN CERTIFICATE-----\n" +
            "MIIDRDCCAiygAwIBAgIRAIKbjwNyFKRfdTDoAOXI2yowDQYJKoZIhvcNAQELBQAw\n" +
            "EzERMA8GA1UEAwwIQ2hhbmdlTWUwHhcNMTkwODExMDcwNDUxWhcNMjkwODA4MDcw\n" +
            "NDUxWjARMQ8wDQYDVQQDDAZjbGllbnQwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAw\n" +
            "ggEKAoIBAQCo8gWo0/ROaV4XSnE7dkZBNQjkRwzPgUhKlW34XQQuA/IpPBhtXH5M\n" +
            "EhbhtYrVmvi/DBeWE6hDbi5y/4E7lvGCmmnpExnsiVoxrz3CcF+K1h39iDcKd3e9\n" +
            "56sJGLEWOGfIKqm1W7XNy6fguvKFSrrcfyT8t87p0s+TD1ZyAF5reR7tNBOc9qHf\n" +
            "pPKDGc7I/AlaujRQ+W8H3oAvcz51tKTdo6jUsE95WpCA9csvZj4hFbdGFnEKWslU\n" +
            "40z1UzAnTq/vUE1+9B2YhoDIsntqqQPlR2xXJ5ps85/ZF9XQl4yMpbsOBbBEctOH\n" +
            "606bjafZ+thUxnO+IhPbowM23eLiKCOVAgMBAAGjgZQwgZEwCQYDVR0TBAIwADAd\n" +
            "BgNVHQ4EFgQUcHgwozO+kU3l76YZzrLsIbCZi2EwQwYDVR0jBDwwOoAUFQ1PKbvA\n" +
            "HjBD4FF6yeY/7tjpIJmhF6QVMBMxETAPBgNVBAMMCENoYW5nZU1lggkA6fNPgWbI\n" +
            "0TkwEwYDVR0lBAwwCgYIKwYBBQUHAwIwCwYDVR0PBAQDAgeAMA0GCSqGSIb3DQEB\n" +
            "CwUAA4IBAQACcbQ73rPp97/QWHrpW7SLKOmfllCLq5g5WOAPRVWCuq+saI6JAWoz\n" +
            "B9r5sU0Vcj8HJLyybu7oVvIjhLQxq3XHFpIb0LfJBsUs2BrvBTUhP+UL2TQwPTiN\n" +
            "G/qefzYfcAijX8EbW//0CF91/DtlzFLfhWs0nt0PvwiT9xvQ8BSz3UIvElX52fMe\n" +
            "PC34bXixqtWamk3BZGg6HP0Vk53BLD/bsIEONfK29f4BZK8kyZV5YoCTqLQ1+Oss\n" +
            "QMZcGhhpnf6ibHUHHiIpMa4qjD+NX7Dje42sU+/CcBD7DNApWdU056/A0T65t20s\n" +
            "3jBcl2pN//TLBw5Iwt79/YdBZCmN9HU5\n" +
            "-----END CERTIFICATE-----\n" +
            "</cert>\n" +
            "<key>\n" +
            "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQCo8gWo0/ROaV4X\n" +
            "SnE7dkZBNQjkRwzPgUhKlW34XQQuA/IpPBhtXH5MEhbhtYrVmvi/DBeWE6hDbi5y\n" +
            "/4E7lvGCmmnpExnsiVoxrz3CcF+K1h39iDcKd3e956sJGLEWOGfIKqm1W7XNy6fg\n" +
            "uvKFSrrcfyT8t87p0s+TD1ZyAF5reR7tNBOc9qHfpPKDGc7I/AlaujRQ+W8H3oAv\n" +
            "cz51tKTdo6jUsE95WpCA9csvZj4hFbdGFnEKWslU40z1UzAnTq/vUE1+9B2YhoDI\n" +
            "sntqqQPlR2xXJ5ps85/ZF9XQl4yMpbsOBbBEctOH606bjafZ+thUxnO+IhPbowM2\n" +
            "3eLiKCOVAgMBAAECggEBAII2h8YoFxSYgbzR05RRBLUde0z2thj5PA7APZI/nwkY\n" +
            "YX0GpI0lzPI0vw4YLnmUiEQ3vbYeUIIoWgSoiaK0JFDVUuwhnMmC09GXj7JdikYn\n" +
            "4dWXN1UJ33CvLlREg7jsoJDW0RbHKMkFPs8nR+UURBHOrWNbCE+P/A0MtUQIpL2P\n" +
            "zGFI/1yo5sPF8thCc47gx7Grg+nN+hdx0IwSsOw7ounqHabv0dGDhyu6TP5j6Onw\n" +
            "NOBFUW/mxTfyQYIowKUBmV1jgf/UiX6OnwqLjAlkute6TyNs54SZsLO1/i8bdyDj\n" +
            "3+wx9dsvn/YxXRfGgQ58JjNT9YFi1F7nArKhdHxJxgECgYEA1eQGGXKtHSXSkpXX\n" +
            "xppIlsrXxGN9FYsQiZ9BKhwIBvvhPOYH995Y8sIXcaj+3Jk0fQ1g7MfN/lQfYV8D\n" +
            "Ll37RbSOIvcvv/CPAS+6NkyjIqKHXE4ia/O2DwIwggX+kVaUo4m8xYT4tDMf3N/+\n" +
            "aa2roMToLxhwSIr835wMGSqdEU0CgYEAyjTHUH86LNdvp7NAgzBM/3vCDd0D1KKc\n" +
            "8coohrEv/HxAEnwr/MdBELwF4JOJKIkhiZsZp1hnD3L7lLfAI8Y7qfia6/5Jd8BV\n" +
            "aZNm7IrXZOMelA+HCBxOPk2qgB8gC4dYCorDMJSiSU5hxI9Y8vpKkjkNAxmHhG8U\n" +
            "vLv2z1LWt2kCgYEAoNfXal2dJtUhc4/w8m1k9thUx19VWdT4u7UhqMvpkW7qYwRM\n" +
            "q63tz6f7P0KpXTRsjzl3ULe0BXQ3IN71InIUGcf8NvkRVFJPB8JfUqPw7YOxLM2o\n" +
            "/usHVmP8HFrro1mrbwuOBC7JY6EqbcuXotqxvLU2MOw8y6CRJopPYsFz2eUCgYEA\n" +
            "r2Tuio2BWcoZqN6ck+7+qxGfdsUCgJv5BOZ1Frh1Rk5EiYQ5546yiqB9fJqc+5NP\n" +
            "ivwFWZod81Gqlgp5WM6njDz/rLqq4Xvs2qIoQgAA7BrxuD5rw4ZB/FsEDRRyiG2C\n" +
            "2coZm3ch+fJKbeRlpIWkz4c7GTg4y5BK7ZchdB6H7xkCgYEAjFSbHCr0s5rCvguN\n" +
            "ZfTGeH6fk9U0rKtKglg41gBUsUO1h52HeWZA7b3wUtsMMW2PBLD/6e+qtqB7rK/r\n" +
            "TBk6cnfc+Qqa01gb4pXa4E9DTcRe8W+0LyxK+KQUjB00EAmjCYtp3u8nQVOaMNHh\n" +
            "EMcwSdJK6kI5gAm+9OU6o2fzFl4=\n" +
            "-----END PRIVATE KEY-----\n" +
            "</key>\n" +
            "<tls-auth>\n" +
            "-----BEGIN OpenVPN Static key V1-----\n" +
            "0abd635335d685b6ea0871bebc7424b6\n" +
            "5bf85b4582a125424481e9f759dfc62c\n" +
            "abc12999c6e05d369ca663e255305104\n" +
            "063b2bffcb92d61e7991cc92eb5bb3df\n" +
            "b15fa01c7ff384985421e0b7ca9f9954\n" +
            "9e4972c43092bc3a860ad9140670fd5a\n" +
            "f908cdd993e955a27529af7321b73853\n" +
            "b448229f5779a4b3ccac63b05ad413ed\n" +
            "17ac894f306f2f3275a12b104ec13889\n" +
            "d8ea9c0986f2d31e4bc795694e77ba7b\n" +
            "dd27c16827c4ea54bc97cb5649d11378\n" +
            "48f8d11d11703023d6ea96fd04c87450\n" +
            "918c262294c320eef975c7834a554075\n" +
            "f5b95079984dd827fd2e841e7cfa6a09\n" +
            "cdf012a7bbe2c1a4079687631459b301\n" +
            "96bf4f244a9c316fadab24dd25668ca1\n" +
            "-----END OpenVPN Static key V1-----\n" +
            "</tls-auth>\n";

    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindUtils.bind(this);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadVpnProfile(data);
                getVpnProfile().mName = "vpn";

                setAccountAndPassword("123", "456");
                connectVpn();
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopVpn();
            }
        });

        findViewById(R.id.btn_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "isRunning: " + isRunning());
            }
        });
    }

    @BindStatus
    public void onStatus(StatusInfo statusInfo) {
        Log.d(TAG, "onStatus: " + statusInfo.toString());
        switch (statusInfo.getLevel()) {
            case LEVEL_START:
                // 开始连接
                break;
            case LEVEL_CONNECTED:
                // 已连接
                break;
            case LEVEL_VPNPAUSED:
                // 暂停
                break;
            case LEVEL_NONETWORK:
                // 无网络
                break;
            case LEVEL_CONNECTING_SERVER_REPLIED:
                // 服务器答应
                break;
            case LEVEL_CONNECTING_NO_SERVER_REPLY_YET:
                // 服务器不答应
                break;
            case LEVEL_NOTCONNECTED:
                // 连接关闭
                break;
            case LEVEL_AUTH_FAILED:
                // 认证失败
                break;
            case LEVEL_WAITING_FOR_USER_INPUT:
                // 等待用户输入
                break;
            case UNKNOWN_LEVEL:
                // 未知错误
                break;
        }
    }

    @Override
    protected void onDestroy() {
        BindUtils.unBind(this);
        super.onDestroy();
    }

    @Override
    public Intent getJumpIntent() {
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), MainActivity.class.getName());
        intent.setFlags(FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }
}
