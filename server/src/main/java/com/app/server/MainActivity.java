//package com.app.server;
//
//import android.app.Activity;
//import android.content.ActivityNotFoundException;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.net.VpnService;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//
//import com.app.vpn.bean.Server;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import de.blinkt.openvpn.VpnProfile;
//import de.blinkt.openvpn.core.ConfigParser;
//import de.blinkt.openvpn.core.OpenVPNService;
//import de.blinkt.openvpn.core.ProfileManager;
//import de.blinkt.openvpn.core.VPNLaunchHelper;
//import de.blinkt.openvpn.core.VpnStatus;
//
//
//public class MainActivity extends AppCompatActivity {
//
//    private static final int START_VPN_PROFILE = 70;
//    public final static String BROADCAST_ACTION = "de.blinkt.openvpn.VPN_STATUS";
//    private static OpenVPNService mVPNService;
//    private boolean isBindedService = false;
//    private Server mServer;
//    private VpnProfile vpnProfile;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mServer = new Server();
//        mServer.setHostName("vpn264214163");
//        mServer.setIp("47.104.81.39");
//        mServer.setScore("1351560");
//        mServer.setPing("30");
//        mServer.setSpeed("49973622");
//        mServer.setCountryLong("Korea Republic of");
//        mServer.setCountryShort("KR");
//        mServer.setNumVpnSessions("51");
//        mServer.setUptime("1182208542");
//        mServer.setTotalUsers("43583");
//        mServer.setTotalTraffic("6956167170544");
//        mServer.setLogType("2weeks");
//        mServer.setOperator("marimo@marimo.moe");
//        mServer.setMessage("TESTING...");
//        mServer.setConfigData("IyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIw0KIyBPcGVuVlBOIDIuMCBTYW1wbGUgQ29uZmlndXJhdGlvbiBGaWxlDQojIGZvciBQYWNrZXRpWCBWUE4gLyBTb2Z0RXRoZXIgVlBOIFNlcnZlcg0KIyANCiMgISEhIEFVVE8tR0VORVJBVEVEIEJZIFNPRlRFVEhFUiBWUE4gU0VSVkVSIE1BTkFHRU1FTlQgVE9PTCAhISENCiMgDQojICEhISBZT1UgSEFWRSBUTyBSRVZJRVcgSVQgQkVGT1JFIFVTRSBBTkQgTU9ESUZZIElUIEFTIE5FQ0VTU0FSWSAhISENCiMgDQojIFRoaXMgY29uZmlndXJhdGlvbiBmaWxlIGlzIGF1dG8tZ2VuZXJhdGVkLiBZb3UgbWlnaHQgdXNlIHRoaXMgY29uZmlnIGZpbGUNCiMgaW4gb3JkZXIgdG8gY29ubmVjdCB0byB0aGUgUGFja2V0aVggVlBOIC8gU29mdEV0aGVyIFZQTiBTZXJ2ZXIuDQojIEhvd2V2ZXIsIGJlZm9yZSB5b3UgdHJ5IGl0LCB5b3Ugc2hvdWxkIHJldmlldyB0aGUgZGVzY3JpcHRpb25zIG9mIHRoZSBmaWxlDQojIHRvIGRldGVybWluZSB0aGUgbmVjZXNzaXR5IHRvIG1vZGlmeSB0byBzdWl0YWJsZSBmb3IgeW91ciByZWFsIGVudmlyb25tZW50Lg0KIyBJZiBuZWNlc3NhcnksIHlvdSBoYXZlIHRvIG1vZGlmeSBhIGxpdHRsZSBhZGVxdWF0ZWx5IG9uIHRoZSBmaWxlLg0KIyBGb3IgZXhhbXBsZSwgdGhlIElQIGFkZHJlc3Mgb3IgdGhlIGhvc3RuYW1lIGFzIGEgZGVzdGluYXRpb24gVlBOIFNlcnZlcg0KIyBzaG91bGQgYmUgY29uZmlybWVkLg0KIyANCiMgTm90ZSB0aGF0IHRvIHVzZSBPcGVuVlBOIDIuMCwgeW91IGhhdmUgdG8gcHV0IHRoZSBjZXJ0aWZpY2F0aW9uIGZpbGUgb2YNCiMgdGhlIGRlc3RpbmF0aW9uIFZQTiBTZXJ2ZXIgb24gdGhlIE9wZW5WUE4gQ2xpZW50IGNvbXB1dGVyIHdoZW4geW91IHVzZSB0aGlzDQojIGNvbmZpZyBmaWxlLiBQbGVhc2UgcmVmZXIgdGhlIGJlbG93IGRlc2NyaXB0aW9ucyBjYXJlZnVsbHkuDQoNCg0KIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIw0KIyBTcGVjaWZ5IHRoZSB0eXBlIG9mIHRoZSBsYXllciBvZiB0aGUgVlBOIGNvbm5lY3Rpb24uDQojIA0KIyBUbyBjb25uZWN0IHRvIHRoZSBWUE4gU2VydmVyIGFzIGEgIlJlbW90ZS1BY2Nlc3MgVlBOIENsaWVudCBQQyIsDQojICBzcGVjaWZ5ICYjMzk7ZGV2IHR1biYjMzk7LiAoTGF5ZXItMyBJUCBSb3V0aW5nIE1vZGUpDQojDQojIFRvIGNvbm5lY3QgdG8gdGhlIFZQTiBTZXJ2ZXIgYXMgYSBicmlkZ2luZyBlcXVpcG1lbnQgb2YgIlNpdGUtdG8tU2l0ZSBWUE4iLA0KIyAgc3BlY2lmeSAmIzM5O2RldiB0YXAmIzM5Oy4gKExheWVyLTIgRXRoZXJuZXQgQnJpZGdpbmUgTW9kZSkNCg0KZGV2IHR1bg0KDQoNCiMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMNCiMgU3BlY2lmeSB0aGUgdW5kZXJseWluZyBwcm90b2NvbCBiZXlvbmQgdGhlIEludGVybmV0Lg0KIyBOb3RlIHRoYXQgdGhpcyBzZXR0aW5nIG11c3QgYmUgY29ycmVzcG9uZCB3aXRoIHRoZSBsaXN0ZW5pbmcgc2V0dGluZyBvbg0KIyB0aGUgVlBOIFNlcnZlci4NCiMgDQojIFNwZWNpZnkgZWl0aGVyICYjMzk7cHJvdG8gdGNwJiMzOTsgb3IgJiMzOTtwcm90byB1ZHAmIzM5Oy4NCg0KcHJvdG8gdGNwDQoNCg0KIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIw0KIyBUaGUgZGVzdGluYXRpb24gaG9zdG5hbWUgLyBJUCBhZGRyZXNzLCBhbmQgcG9ydCBudW1iZXIgb2YNCiMgdGhlIHRhcmdldCBWUE4gU2VydmVyLg0KIyANCiMgWW91IGhhdmUgdG8gc3BlY2lmeSBhcyAmIzM5O3JlbW90ZSAmbHQ7SE9TVE5BTUUmZ3Q7ICZsdDtQT1JUJmd0OyYjMzk7LiBZb3UgY2FuIGFsc28NCiMgc3BlY2lmeSB0aGUgSVAgYWRkcmVzcyBpbnN0ZWFkIG9mIHRoZSBob3N0bmFtZS4NCiMgDQojIE5vdGUgdGhhdCB0aGUgYXV0by1nZW5lcmF0ZWQgYmVsb3cgaG9zdG5hbWUgYXJlIGEgImF1dG8tZGV0ZWN0ZWQNCiMgSVAgYWRkcmVzcyIgb2YgdGhlIFZQTiBTZXJ2ZXIuIFlvdSBoYXZlIHRvIGNvbmZpcm0gdGhlIGNvcnJlY3RuZXNzDQojIGJlZm9yZWhhbmQuDQojIA0KIyBXaGVuIHlvdSB3YW50IHRvIGNvbm5lY3QgdG8gdGhlIFZQTiBTZXJ2ZXIgYnkgdXNpbmcgVENQIHByb3RvY29sLA0KIyB0aGUgcG9ydCBudW1iZXIgb2YgdGhlIGRlc3RpbmF0aW9uIFRDUCBwb3J0IHNob3VsZCBiZSBzYW1lIGFzIG9uZSBvZg0KIyB0aGUgYXZhaWxhYmxlIFRDUCBsaXN0ZW5lcnMgb24gdGhlIFZQTiBTZXJ2ZXIuDQojIA0KIyBXaGVuIHlvdSB1c2UgVURQIHByb3RvY29sLCB0aGUgcG9ydCBudW1iZXIgbXVzdCBzYW1lIGFzIHRoZSBjb25maWd1cmF0aW9uDQojIHNldHRpbmcgb2YgIk9wZW5WUE4gU2VydmVyIENvbXBhdGlibGUgRnVuY3Rpb24iIG9uIHRoZSBWUE4gU2VydmVyLg0KDQpyZW1vdGUgMS4yNDcuMjIzLjUwIDE5OTkNCg0KDQojIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjDQojIFRoZSBIVFRQL0hUVFBTIHByb3h5IHNldHRpbmcuDQojIA0KIyBPbmx5IGlmIHlvdSBoYXZlIHRvIHVzZSB0aGUgSW50ZXJuZXQgdmlhIGEgcHJveHksIHVuY29tbWVudCB0aGUgYmVsb3cNCiMgdHdvIGxpbmVzIGFuZCBzcGVjaWZ5IHRoZSBwcm94eSBhZGRyZXNzIGFuZCB0aGUgcG9ydCBudW1iZXIuDQojIEluIHRoZSBjYXNlIG9mIHVzaW5nIHByb3h5LWF1dGhlbnRpY2F0aW9uLCByZWZlciB0aGUgT3BlblZQTiBtYW51YWwuDQoNCjtodHRwLXByb3h5LXJldHJ5DQo7aHR0cC1wcm94eSBbcHJveHkgc2VydmVyXSBbcHJveHkgcG9ydF0NCg0KDQojIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjDQojIFRoZSBlbmNyeXB0aW9uIGFuZCBhdXRoZW50aWNhdGlvbiBhbGdvcml0aG0uDQojIA0KIyBEZWZhdWx0IHNldHRpbmcgaXMgZ29vZC4gTW9kaWZ5IGl0IGFzIHlvdSBwcmVmZXIuDQojIFdoZW4geW91IHNwZWNpZnkgYW4gdW5zdXBwb3J0ZWQgYWxnb3JpdGhtLCB0aGUgZXJyb3Igd2lsbCBvY2N1ci4NCiMgDQojIFRoZSBzdXBwb3J0ZWQgYWxnb3JpdGhtcyBhcmUgYXMgZm9sbG93czoNCiMgIGNpcGhlcjogW05VTEwtQ0lQSEVSXSBOVUxMIEFFUy0xMjgtQ0JDIEFFUy0xOTItQ0JDIEFFUy0yNTYtQ0JDIEJGLUNCQw0KIyAgICAgICAgICBDQVNULUNCQyBDQVNUNS1DQkMgREVTLUNCQyBERVMtRURFLUNCQyBERVMtRURFMy1DQkMgREVTWC1DQkMNCiMgICAgICAgICAgUkMyLTQwLUNCQyBSQzItNjQtQ0JDIFJDMi1DQkMNCiMgIGF1dGg6ICAgU0hBIFNIQTEgTUQ1IE1ENCBSTUQxNjANCg0KY2lwaGVyIEFFUy0xMjgtQ0JDDQphdXRoIFNIQTENCg0KDQojIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjDQojIE90aGVyIHBhcmFtZXRlcnMgbmVjZXNzYXJ5IHRvIGNvbm5lY3QgdG8gdGhlIFZQTiBTZXJ2ZXIuDQojIA0KIyBJdCBpcyBub3QgcmVjb21tZW5kZWQgdG8gbW9kaWZ5IGl0IHVubGVzcyB5b3UgaGF2ZSBhIHBhcnRpY3VsYXIgbmVlZC4NCg0KcmVzb2x2LXJldHJ5IGluZmluaXRlDQpub2JpbmQNCnBlcnNpc3Qta2V5DQpwZXJzaXN0LXR1bg0KY2xpZW50DQp2ZXJiIDMNCiNhdXRoLXVzZXItcGFzcw0KDQoNCiMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMNCiMgVGhlIGNlcnRpZmljYXRlIGZpbGUgb2YgdGhlIGRlc3RpbmF0aW9uIFZQTiBTZXJ2ZXIuDQojIA0KIyBUaGUgQ0EgY2VydGlmaWNhdGUgZmlsZSBpcyBlbWJlZGRlZCBpbiB0aGUgaW5saW5lIGZvcm1hdC4NCiMgWW91IGNhbiByZXBsYWNlIHRoaXMgQ0EgY29udGVudHMgaWYgbmVjZXNzYXJ5Lg0KIyBQbGVhc2Ugbm90ZSB0aGF0IGlmIHRoZSBzZXJ2ZXIgY2VydGlmaWNhdGUgaXMgbm90IGEgc2VsZi1zaWduZWQsIHlvdSBoYXZlIHRvDQojIHNwZWNpZnkgdGhlIHNpZ25lciYjMzk7cyByb290IGNlcnRpZmljYXRlIChDQSkgaGVyZS4NCg0KJmx0O2NhJmd0Ow0KLS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tDQpNSUlGMkRDQ0E4Q2dBd0lCQWdJUVRLcjV5dHRqYitBZjkwN1lXd09HblRBTkJna3Foa2lHOXcwQkFRd0ZBRENCDQpoVEVMTUFrR0ExVUVCaE1DUjBJeEd6QVpCZ05WQkFnVEVrZHlaV0YwWlhJZ1RXRnVZMmhsYzNSbGNqRVFNQTRHDQpBMVVFQnhNSFUyRnNabTl5WkRFYU1CZ0dBMVVFQ2hNUlEwOU5UMFJQSUVOQklFeHBiV2wwWldReEt6QXBCZ05WDQpCQU1USWtOUFRVOUVUeUJTVTBFZ1EyVnlkR2xtYVdOaGRHbHZiaUJCZFhSb2IzSnBkSGt3SGhjTk1UQXdNVEU1DQpNREF3TURBd1doY05Nemd3TVRFNE1qTTFPVFU1V2pDQmhURUxNQWtHQTFVRUJoTUNSMEl4R3pBWkJnTlZCQWdUDQpFa2R5WldGMFpYSWdUV0Z1WTJobGMzUmxjakVRTUE0R0ExVUVCeE1IVTJGc1ptOXlaREVhTUJnR0ExVUVDaE1SDQpRMDlOVDBSUElFTkJJRXhwYldsMFpXUXhLekFwQmdOVkJBTVRJa05QVFU5RVR5QlNVMEVnUTJWeWRHbG1hV05oDQpkR2x2YmlCQmRYUm9iM0pwZEhrd2dnSWlNQTBHQ1NxR1NJYjNEUUVCQVFVQUE0SUNEd0F3Z2dJS0FvSUNBUUNSDQo2RlNTMGdwV3Nhd05KTjNGejBSbmRKa3JONk45STNBQWNieFQzOFQ2S2hLUFMzOFFWcjJmY0hLM1lYL0pTdzhYDQpwejNqc0FSaDd2OFJsOGYwaGo0SytqNWMrWlBtTkhyWkZHdm5uTE9Gb0lKNmRxOXhrTmZzL1EzNm5HejYzN0NDDQo5QlIrK2I3RXBpOVBmNWwvdGZ4blEzSzlEQURXaWV0ckxOUHRqNWdjRkt0KzVlTnUvTmlvNUpJazJrTnJZcmhWDQovZXJCdkd5MmkvTU9qWnJrbTJ4cG1maDRTREJGMWEzaERUeEZZUHd5bGxFbnZHZkR5aTYyYStwR3g4Y2dvTEVmDQpaZDVJQ0xxa1RxbnlnMFkzaE92b3pJRklRMmRPY2lxYlhMMU1HeWlLWENKN3RLdVkyZTdnVVlQRENVWk9iVDZaDQorcFVYMm53elYwRThqVkh0QzdaY3J5eGpHdDlYeUQrODZWM0VtNjlGbWVLaldpUzB1cWxXUGM5dnF2OUpXTDd3DQpxUC8wdUszcE4vdTZ1UFFMT3Zub1EwSWVpZGlFeXhQeDJidmhpV0M0akNoV3JCUWRuQXJuY2V2UER0MDlxWmFoDQpTTDA4OTYrMURTSk13QkdCN0ZZNzl0T2k0bHUzc2dRaVVwV0FrMm5vamt4bDhaRURMWEIwQXVxTFp4VXBhVklDDQp1OWZmVUdwVlJyK2dveWhoZjNEUXc2S3FMQ0dxUjg0b25BWkZkcitDR0NlMDFhNjB5MURtYS9STWhuRXc2YWJmDQpGb2JnMlA5QTNmdlFRb2gvb3pNNkxsd2VRUkdCWTg0WWNXc3I3S2FLdHpGY09tcEg0TU41V2RZZ0dxL3lhcGlxDQpjcnhYU3RKTG5ic1EvTEJNUWVYdEhUMWVLSjJjekwrelVkcW5SK1dFVXdJREFRQUJvMEl3UURBZEJnTlZIUTRFDQpGZ1FVdTY5K0FqMzZwdkU4aEk2dDdqaVk3Tmt5TXRRd0RnWURWUjBQQVFIL0JBUURBZ0VHTUE4R0ExVWRFd0VCDQovd1FGTUFNQkFmOHdEUVlKS29aSWh2Y05BUUVNQlFBRGdnSUJBQXJ4MVVhRXQ2NVJ1Mnl5VFVFVUFKTk1uTXZsDQp3RlRQb0NXT0F2bjlzS0lOOVNDWVBCTXRyRmFpc05aK0VaTHBMcnFlTHBweXNiMFpSR3hoTmFLYXRCWVNhVnFNDQo0ZGMrcEJyb0x3UDBybUVkRUJzcXBJdDZ4ZjRGcHVIQTFzaitucTZQSzdvOW1malljd2xZUm02bW5QVFhKOU9WDQoyamVEY2h6VGMrQ2lSNWtET0YzVlNYa0FLUnpIN0pzZ0hBY2thVmQ0c2puOE9vU2d0Wng4amI4dWsySW50em5hDQpGeGl1dlR3SmFQK0VtenpWMWdzRDQxZWVGUGZSNjAvSXZZY2p0N1pKUTNtRlhMcnJrZ3VoeHVob3FFd1dzUnFaDQpDdWhUTEpLN29Ra1lkUXhscUh2TEk3Y2F3aWlGd3h2LzBDdGk3NlI3Q1pHWVo0d1VBYzFvQm1waklYVURnSWlLDQpib0hHaGZLcHBDM245S1VrRUVlRHlzMzBqWGxZc1FhYjV4b3EyWjBCMTVSOTdRTkt5dkRiNktrQlB2VldtY2tlDQpqa2s5dStVSnVlQlBTWkk5Rm9KQXpNeFp4dVk2N1JJdWFUeHNsYkg5cWgxN2Y0YStIZzR5UnZ2N0U0OTFmMHlMDQpTMFpqL2dBMFFIREJ3N21oM2FadzRnU3pRYnpwZ0pIcVpKeDY0U0lEcVp4dWJ3NWxUMnlIaDE3emJxRDVkYVdiDQpRT2hUc2llZFNybkFkeUdOLzRmeTNyeU03eGZmdDBrTDBmSnVNQXNhRGs1MjdSSDg5ZWxXc24yL3gyMEtrNHlsDQowTUMySGI0NlRwU2kxMjVzQzhLS2ZQb2c4OFRrNWMwTnFNdVJrckY4aGV5MUZHbG1Eb0xuemM3SUxhWlJmeUhCDQpOVk9GQmtwZG42MjdHMTkwDQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tDQoNCiZsdDsvY2EmZ3Q7DQoNCg0KIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIw0KIyBUaGUgY2xpZW50IGNlcnRpZmljYXRlIGZpbGUgKGR1bW15KS4NCiMgDQojIEluIHNvbWUgaW1wbGVtZW50YXRpb25zIG9mIE9wZW5WUE4gQ2xpZW50IHNvZnR3YXJlDQojIChmb3IgZXhhbXBsZTogT3BlblZQTiBDbGllbnQgZm9yIGlPUyksDQojIGEgcGFpciBvZiBjbGllbnQgY2VydGlmaWNhdGUgYW5kIHByaXZhdGUga2V5IG11c3QgYmUgaW5jbHVkZWQgb24gdGhlDQojIGNvbmZpZ3VyYXRpb24gZmlsZSBkdWUgdG8gdGhlIGxpbWl0YXRpb24gb2YgdGhlIGNsaWVudC4NCiMgU28gdGhpcyBzYW1wbGUgY29uZmlndXJhdGlvbiBmaWxlIGhhcyBhIGR1bW15IHBhaXIgb2YgY2xpZW50IGNlcnRpZmljYXRlDQojIGFuZCBwcml2YXRlIGtleSBhcyBmb2xsb3dzLg0KDQombHQ7Y2VydCZndDsNCi0tLS0tQkVHSU4gQ0VSVElGSUNBVEUtLS0tLQ0KTUlJQ3hqQ0NBYTRDQVFBd0RRWUpLb1pJaHZjTkFRRUZCUUF3S1RFYU1CZ0dBMVVFQXhNUlZsQk9SMkYwWlVOcw0KYVdWdWRFTmxjblF4Q3pBSkJnTlZCQVlUQWtwUU1CNFhEVEV6TURJeE1UQXpORGswT1ZvWERUTTNNREV4T1RBeg0KTVRRd04xb3dLVEVhTUJnR0ExVUVBeE1SVmxCT1IyRjBaVU5zYVdWdWRFTmxjblF4Q3pBSkJnTlZCQVlUQWtwUQ0KTUlJQklqQU5CZ2txaGtpRzl3MEJBUUVGQUFPQ0FROEFNSUlCQ2dLQ0FRRUE1aDJsZ1FRWVVqd29LWUpielZaQQ0KNVZjSUdkNW90UGMvcVpSTXQwS0l0Q0ZBMHM5UndSZU5WYTlmRFJGTFJCaGNJVE9sdjNGQmNXM0U4aDFVczdSRA0KNFc4R21KZTh6YXBKbkxzRDM5T1NNUkN6WkpuY3pXNE9DSDFQWlJaV0txRHRqbE5jYTlBRjhhNjVqVG1sRHhDUQ0KQ2pudExJV2s1T0xMVmtGdDkvdFNjYzFHRHRjaTU1b2ZoYU5BWU1QaUg3VjgrMWc2NnBHSFhBb1dLNkFRVkg2Nw0KWENLSm5HQjVubFErSHNNWVBWL080OUxkOTFaTi8ydEhrY2FMTHlOdHl3eFZQUlNzUmg0ODBqanUwZmNDc3Y2aA0KcC8weVhuVEIvL21XdXRCR3BkVWxJYndpSVRiQW1yc2JZbmppZ1J2blBxWDFSTkpVYmk5RnA2QzJjL0hJRkpHRA0KeXdJREFRQUJNQTBHQ1NxR1NJYjNEUUVCQlFVQUE0SUJBUUNoTzVoZ2N3LzRvV2ZvRUZMdTlrQmExQi8va3hIOA0KaFFrQ2hWTm44QlJDN1kwVVJRaXRQbDNES0VlZDlVUkJEZGcyS09Bejc3YmI2RU5QaWxpRCthMzhVSkhJUk1xZQ0KVUJIaGxsT0hJenZEaEhGYmFvdkFMQlFjZWVCemRrUXhzS1FFU0ttUW1SODMyOTUwVUNvdm95UkI2MVV5QVY3aA0KK21aaFlQR1JLWEtTSkk2czBFZ2cvQ3JpK0N3azRiakpmcmI1aFZzZTExeWg0RDlNSGh3U2ZDT0grMHo0aFBVVA0KRmt1N2RHYXZVUk81U1Z4TW4vc0w2RW41RCtvU2VYa2FkSHBEcytBaXJ5bTJZSGgxNWgwK2pQU09vUjZ5aVZwLw0KNnpaZVprck40M2t1UzczS3BLREZqZkZQaDh0NHIxZ09JanR0a05jUXFCY2N1c25wbFE3SEpwc2sNCi0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0NCg0KJmx0Oy9jZXJ0Jmd0Ow0KDQombHQ7a2V5Jmd0Ow0KLS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQ0KTUlJRXBBSUJBQUtDQVFFQTVoMmxnUVFZVWp3b0tZSmJ6VlpBNVZjSUdkNW90UGMvcVpSTXQwS0l0Q0ZBMHM5Ug0Kd1JlTlZhOWZEUkZMUkJoY0lUT2x2M0ZCY1czRThoMVVzN1JENFc4R21KZTh6YXBKbkxzRDM5T1NNUkN6WkpuYw0Kelc0T0NIMVBaUlpXS3FEdGpsTmNhOUFGOGE2NWpUbWxEeENRQ2pudExJV2s1T0xMVmtGdDkvdFNjYzFHRHRjaQ0KNTVvZmhhTkFZTVBpSDdWOCsxZzY2cEdIWEFvV0s2QVFWSDY3WENLSm5HQjVubFErSHNNWVBWL080OUxkOTFaTg0KLzJ0SGtjYUxMeU50eXd4VlBSU3NSaDQ4MGpqdTBmY0NzdjZocC8weVhuVEIvL21XdXRCR3BkVWxJYndpSVRiQQ0KbXJzYlluamlnUnZuUHFYMVJOSlViaTlGcDZDMmMvSElGSkdEeXdJREFRQUJBb0lCQUVSVjdYNUF2eEE4dVJpSw0KazhTSXBzRDBkWDFwSk9NSXdha1VWeXZjNEVmTjBEaEtSTmI0cllvU2lFR1RMeXpMcHlCYy9BMjhEbGttNWVPWQ0KZmp6WGZZa0d0WWkvRnR4a2czTzl2Y3JNUTQrNmkrdUdIYUlMMnJMK3M0TXJmTzh2MXh2NitXa3kzM0VFR0NvdQ0KUWl3VkdSRlFYblJvUTYyTkJDRmJVTkxobVh3ZGoxYWtaekxVNHA1UjR6QTNRaGR4d0VJYXRWTHQwKzdvd0xRMw0KbFA4c2ZYaHBwUE9YalRxTUQ0UWtZd3pQQWE4L3pGN2FjbjRrcnlyVVA3UTZQQWZkMHpFVnFOeTlaQ1o5ZmZobw0KelhlZEZqNDg2SUZvYzVnblRwMk42anNuVmo0TENHSWhsVkhsWUdvektLRnFKY1FWR3NIQ3FxMW96MnpqVzZMUw0Kb1JZSUhnRUNnWUVBOHpacmtDd05ZU1hKdU9ESjNtL2hPTFZ4Y3hnSnV3WG9pRXJXZDBFNDJ2UGFuampWTWhudA0KS1k1bDhxR01KNkZoSzlMWXgycUNyZi9FMFh0VUFaMndWcTNPUlR5R25zTVdyZTl0TFlzNTVYK1pOMTBUYzc1eg0KNGhhY2JVMGhxS04xSGlEbXNNUlkzLzJOYVpIb3k3TUtud0pKQmFHNDhsOUNDVGxWd01Ib2NJRUNnWUVBOGpieQ0KZEdqeFRIKzZYSFdOaXpiNVNSYlp4QW55RWVKZVJ3VE1oMGdHendHUHBIL3NaWUd6eXUwU3lTWFdDblpoM1JncQ0KNXVMbE54dHJYcmxqWmx5aTJuUWRRZ3NxMllyV1VzMCt6Z1UrMjJ1UXNacFNBZnRtaFZydHZldDZNalZqYkJ5WQ0KREFEY2lFVlVkSllJWGsrcW5GVUp5ZXJvTElrVGo3V1lLWjZSamtzQ2dZQm9DRkl3UkRlZzQyb0s4OVJGbW5Pcg0KTHltTkFxNCsyb01oc1dsVmI0ZWpXSVdlQWs5bmMrR1hVZnJYc3pSaFMwMW1VblU1cjV5Z1V2UmNhclYvVDNVNw0KVG5NWitJN1k0RGdXUklEZDUxem5oeElCdFlWNWovQy90ODVIanFPa0grOGI2UlRrYmNoYVgzbWF1N2ZwVWZkcw0KRnEwbmhJcTQyZmhFTzhzcmZZWXdnUUtCZ1FDeWhpMU4vOHRhUndwayszL0lERXpRd2piZmR6VWtXV1NEazlYcw0KSC9wa3VSSFdmVE1QM2ZsV3FFWWdXL0xXNDBwZVcySERxNWltZFY4K0FnWnhlL1hNYmFqaTlMZ3dmMVJZMDA1bg0KS3hhWlF6N3lxSHVwV2xMR0Y2OERQSHhrWlZWU2FnRG5WL3N6dFdYNlNGc0NxRlZueElYaWZYR0M0Y1c1Tm05Zw0KdmE4cTRRS0JnUUNFaExWZVVmZHdLdmtaOTRnL0dGejczMVoyaHJkVmhnTVphVS91NnQwVjk1K1llelBOQ1FaQg0Kd21FOU1tbGJxMWVtRGVST2l2akNmb0doUjNrWlhXMXBUS2xMaDZaTVVRVU9wcHRkWHZhOFh4Zm9xUXdhM2VuQQ0KTTdtdUJiRjBYTjdWTzgwaUpQditQbUlaZEVJQWtwd0tmaTIwMVlCK0JhZkNJdUd4SUY1MFZnPT0NCi0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0tDQoNCiZsdDsva2V5Jmd0Ow0KDQo=");
//
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startConnect();
//            }
//        });
//
//    }
//
//    public void startConnect() {
//        loadVpnProfile();
//        Intent intent = VpnService.prepare(this);
//        if (intent != null) {
//            VpnStatus.updateStateString("USER_VPN_PERMISSION", "", R.string.state_user_vpn_permission,
//                    VpnStatus.ConnectionStatus.LEVEL_WAITING_FOR_USER_INPUT);
//            // Start the query
//            try {
//                startActivityForResult(intent, START_VPN_PROFILE);
//            } catch (ActivityNotFoundException ane) {
//                // Shame on you Sony! At least one user reported that
//                // an official Sony Xperia Arc S image triggers this exception
//                VpnStatus.logError(R.string.no_vpn_support_image);
//            }
//        } else {
//            onActivityResult(START_VPN_PROFILE, Activity.RESULT_OK, null);
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Intent intent = new Intent(this, OpenVPNService.class);
//        intent.setAction(OpenVPNService.START_SERVICE);
//        isBindedService = bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case START_VPN_PROFILE:
//                    VPNLaunchHelper.startOpenVpn(vpnProfile, getBaseContext());
//                    break;
//            }
//        }
//    }
//
//    private boolean loadVpnProfile() {
//        byte[] data;
//        try {
//            String s  = "###############################################################################\n" +
//                    "# OpenVPN 2.0 Sample Configuration File\n" +
//                    "# for PacketiX VPN / SoftEther VPN Server\n" +
//                    "# \n" +
//                    "# !!! AUTO-GENERATED BY SOFTETHER VPN SERVER MANAGEMENT TOOL !!!\n" +
//                    "# \n" +
//                    "# !!! YOU HAVE TO REVIEW IT BEFORE USE AND MODIFY IT AS NECESSARY !!!\n" +
//                    "# \n" +
//                    "# This configuration file is auto-generated. You might use this config file\n" +
//                    "# in order to connect to the PacketiX VPN / SoftEther VPN Server.\n" +
//                    "# However, before you try it, you should review the descriptions of the file\n" +
//                    "# to determine the necessity to modify to suitable for your real environment.\n" +
//                    "# If necessary, you have to modify a little adequately on the file.\n" +
//                    "# For example, the IP address or the hostname as a destination VPN Server\n" +
//                    "# should be confirmed.\n" +
//                    "# \n" +
//                    "# Note that to use OpenVPN 2.0, you have to put the certification file of\n" +
//                    "# the destination VPN Server on the OpenVPN Client computer when you use this\n" +
//                    "# config file. Please refer the below descriptions carefully.\n" +
//                    "\n" +
//                    "\n" +
//                    "###############################################################################\n" +
//                    "# Specify the type of the layer of the VPN connection.\n" +
//                    "# \n" +
//                    "# To connect to the VPN Server as a \"Remote-Access VPN Client PC\",\n" +
//                    "#  specify 'dev tun'. (Layer-3 IP Routing Mode)\n" +
//                    "#\n" +
//                    "# To connect to the VPN Server as a bridging equipment of \"Site-to-Site VPN\",\n" +
//                    "#  specify 'dev tap'. (Layer-2 Ethernet Bridgine Mode)\n" +
//                    "\n" +
//                    "dev tun\n" +
//                    "\n" +
//                    "\n" +
//                    "###############################################################################\n" +
//                    "# Specify the underlying protocol beyond the Internet.\n" +
//                    "# Note that this setting must be correspond with the listening setting on\n" +
//                    "# the VPN Server.\n" +
//                    "# \n" +
//                    "# Specify either 'proto tcp' or 'proto udp'.\n" +
//                    "\n" +
//                    "proto tcp\n" +
//                    "\n" +
//                    "\n" +
//                    "###############################################################################\n" +
//                    "# The destination hostname / IP address, and port number of\n" +
//                    "# the target VPN Server.\n" +
//                    "# \n" +
//                    "# You have to specify as 'remote <HOSTNAME> <PORT>'. You can also\n" +
//                    "# specify the IP address instead of the hostname.\n" +
//                    "# \n" +
//                    "# Note that the auto-generated below hostname are a \"auto-detected\n" +
//                    "# IP address\" of the VPN Server. You have to confirm the correctness\n" +
//                    "# beforehand.\n" +
//                    "# \n" +
//                    "# When you want to connect to the VPN Server by using TCP protocol,\n" +
//                    "# the port number of the destination TCP port should be same as one of\n" +
//                    "# the available TCP listeners on the VPN Server.\n" +
//                    "# \n" +
//                    "# When you use UDP protocol, the port number must same as the configuration\n" +
//                    "# setting of \"OpenVPN Server Compatible Function\" on the VPN Server.\n" +
//                    "\n" +
//                    "remote 47.104.81.39 1194\n" +
//                    "\n" +
//                    "\n" +
//                    "###############################################################################\n" +
//                    "# The HTTP/HTTPS proxy setting.\n" +
//                    "# \n" +
//                    "# Only if you have to use the Internet via a proxy, uncomment the below\n" +
//                    "# two lines and specify the proxy address and the port number.\n" +
//                    "# In the case of using proxy-authentication, refer the OpenVPN manual.\n" +
//                    "\n" +
//                    ";http-proxy-retry\n" +
//                    ";http-proxy [proxy server] [proxy port]\n" +
//                    "\n" +
//                    "\n" +
//                    "###############################################################################\n" +
//                    "# The encryption and authentication algorithm.\n" +
//                    "# \n" +
//                    "# Default setting is good. Modify it as you prefer.\n" +
//                    "# When you specify an unsupported algorithm, the error will occur.\n" +
//                    "# \n" +
//                    "# The supported algorithms are as follows:\n" +
//                    "#  cipher: [NULL-CIPHER] NULL AES-128-CBC AES-192-CBC AES-256-CBC BF-CBC\n" +
//                    "#          CAST-CBC CAST5-CBC DES-CBC DES-EDE-CBC DES-EDE3-CBC DESX-CBC\n" +
//                    "#          RC2-40-CBC RC2-64-CBC RC2-CBC\n" +
//                    "#  auth:   SHA SHA1 MD5 MD4 RMD160\n" +
//                    "\n" +
//                    "cipher AES-128-CBC\n" +
//                    "auth SHA1\n" +
//                    "\n" +
//                    "\n" +
//                    "###############################################################################\n" +
//                    "# Other parameters necessary to connect to the VPN Server.\n" +
//                    "# \n" +
//                    "# It is not recommended to modify it unless you have a particular need.\n" +
//                    "\n" +
//                    "resolv-retry infinite\n" +
//                    "nobind\n" +
//                    "persist-key\n" +
//                    "persist-tun\n" +
//                    "client\n" +
//                    "verb 3\n" +
//                    ";auth-user-pass\n" +
//                    "\n" +
//                    "\n" +
//                    "###############################################################################\n" +
//                    "# The certificate file of the destination VPN Server.\n" +
//                    "# \n" +
//                    "# The CA certificate file is embedded in the inline format.\n" +
//                    "# You can replace this CA contents if necessary.\n" +
//                    "# Please note that if the server certificate is not a self-signed, you have to\n" +
//                    "# specify the signer's root certificate (CA) here.\n" +
//                    "\n" +
//                    "<ca>\n" +
//                    "-----BEGIN CERTIFICATE-----\n" +
//                    "MIIDHjCCAgagAwIBAgIJAIiGCeKds0sKMA0GCSqGSIb3DQEBCwUAMA8xDTALBgNV\n" +
//                    "BAMTBG1pbGswHhcNMTkwNTE2MDgwMTI3WhcNMjkwNTEzMDgwMTI3WjAPMQ0wCwYD\n" +
//                    "VQQDEwRtaWxrMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvVyQuW2j\n" +
//                    "Cvt5X77SHYFKa24oXRjZwOUDKXzbRkAB9ASagwvoKhaSL0CDDriCoNeuyyzWhoRv\n" +
//                    "+YkaGRixl9yQwP1blfQYubCM49Lt/9fMhWXTBSqUX8QaG3mdX9dTZ4woOgrIezfm\n" +
//                    "Lkk5opGnOrEhpHFFXa5XsAhRCLQ8enOj25WI6BgxlYLWjdkDWn4W/e6NR2m+KJt2\n" +
//                    "6mO2TuBMKEyTIm3gyZZHl7xOaQGluKmmvTpOVLOFS7RNqwIqi+QpvcUrsLuY1dPm\n" +
//                    "f43O6vgOavvijya8nEzi1drfAAtz2vbKoYJBsKK+2x5QtR4jgg5MW8Yi00k8jzYt\n" +
//                    "cqUVDKUGASvT5QIDAQABo30wezAdBgNVHQ4EFgQUPukXKOjN+0si2YQGaHs1aufD\n" +
//                    "aI8wPwYDVR0jBDgwNoAUPukXKOjN+0si2YQGaHs1aufDaI+hE6QRMA8xDTALBgNV\n" +
//                    "BAMTBG1pbGuCCQCIhgninbNLCjAMBgNVHRMEBTADAQH/MAsGA1UdDwQEAwIBBjAN\n" +
//                    "BgkqhkiG9w0BAQsFAAOCAQEADqEgSbK2TvGQWj56bVmXPse1QBd9t035TbRNR0rZ\n" +
//                    "p8/lMo+FzMSjxIjrwpD0fURgdP+WXbnnvAFSKlvgrVQtKZTXSOTyfLA0s7+nqtB6\n" +
//                    "hub5yMeFKJcQwKUV6ISx0gX0y8XMxxqJetMk+RbuRewUpwMRtDgiEiEIC0WjYUzA\n" +
//                    "/oeh5ZVAcH4c4nBZk2pfJjXK0tvTMUJfeGM+mLjv2aqF66apJ00iYzqSwwnHx/0v\n" +
//                    "QnaCw/RALbZXuuTaR/0Gv6s0iGYnwzyt2Dsi9hFbmyB04oEz7h/kmQtJW1p02oDf\n" +
//                    "qgdHu4KS//97umWSiDd3FyPIrx32DIBRDS/3oY33JH3d/g==\n" +
//                    "-----END CERTIFICATE-----\n" +
//                    "\n" +
//                    "</ca>\n" +
//                    "\n" +
//                    "\n" +
//                    "###############################################################################\n" +
//                    "# The client certificate file (dummy).\n" +
//                    "# \n" +
//                    "# In some implementations of OpenVPN Client software\n" +
//                    "# (for example: OpenVPN Client for iOS),\n" +
//                    "# a pair of client certificate and private key must be included on the\n" +
//                    "# configuration file due to the limitation of the client.\n" +
//                    "# So this sample configuration file has a dummy pair of client certificate\n" +
//                    "# and private key as follows.\n" +
//                    "\n" +
//                    "<cert>\n" +
//                    "-----BEGIN CERTIFICATE-----\n" +
//                    "MIIDOTCCAiGgAwIBAgIQTuwXKdfFf2bqHhzIemwY0zANBgkqhkiG9w0BAQsFADAP\n" +
//                    "MQ0wCwYDVQQDEwRtaWxrMB4XDTE5MDUxNjA4MDY0N1oXDTIyMDQzMDA4MDY0N1ow\n" +
//                    "DzENMAsGA1UEAxMEdGVzdDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEB\n" +
//                    "ANymj1BCF8W1eyW+H+8Qn53LZy7oiCE46EteU3UFitOVjj6abUp1neC8UuPqkGW8\n" +
//                    "2SazavLSDbKMjusdPT15YCtGG9VHd9Y8/w+Cxp8vmLxcVajwzeCnxNpZEEYXxT7U\n" +
//                    "0A/4Vbg69UzLzlzESu9K3WGc1BJ4mucBWNpwpYhAM2LdkfHy1fAaE3xbmIQKFqBB\n" +
//                    "B3QupQsBzYYEGZwr9F1lTxsO4X6CmWzhl0EOsZ6aiGbQcIpWwP/drb4CM/UJlObc\n" +
//                    "kje1m/NHVY2LdtphmfUd2S43m9GyRAl64onzpxaHkKPp1lqFBQHTNlMi+MrL+y+x\n" +
//                    "sEcLwIyFE84eOt1UW4KUPg0CAwEAAaOBkDCBjTAJBgNVHRMEAjAAMB0GA1UdDgQW\n" +
//                    "BBSIORqie+/CG05USgWP7vRmIphfuTA/BgNVHSMEODA2gBQ+6Rco6M37SyLZhAZo\n" +
//                    "ezVq58Noj6ETpBEwDzENMAsGA1UEAxMEbWlsa4IJAIiGCeKds0sKMBMGA1UdJQQM\n" +
//                    "MAoGCCsGAQUFBwMCMAsGA1UdDwQEAwIHgDANBgkqhkiG9w0BAQsFAAOCAQEAh0CD\n" +
//                    "XDrjI86w8eWUroX5+nBHUe5hss/63dqfYPH6VvADoNV6BJJ4w3/LmXb+z9XIlv+M\n" +
//                    "4128Td65jwrqGE1lEhFC1rCpEeEEHOYW8/OwsyJ94X/mBhksmcBXm0Eig5Wpnuhg\n" +
//                    "juSQhFUnmhxT5i6UTWaTN7pb/b+IrFJEQzBkUpHObiRtT2qLRFStXgoOuSPs+Twu\n" +
//                    "EmlU8NeJx9XbJXn9TkYo8aR1mTNLh5oPFVN57S1x7bAuCuy12nE26FqRFDK9LDO7\n" +
//                    "Zleh5JQN+NL5Car+WfYK7MrvD6CGF6257YaSqsPUY8xumHKA71Cr5D/HHoWfeMio\n" +
//                    "96ZaEL8Hpx1IpeW/1g==\n" +
//                    "-----END CERTIFICATE-----\n" +
//                    "\n" +
//                    "</cert>\n" +
//                    "\n" +
//                    "<key>\n" +
//                    "-----BEGIN RSA PRIVATE KEY-----\n" +
//                    "MIIEpQIBAAKCAQEA3KaPUEIXxbV7Jb4f7xCfnctnLuiIITjoS15TdQWK05WOPppt\n" +
//                    "SnWd4LxS4+qQZbzZJrNq8tINsoyO6x09PXlgK0Yb1Ud31jz/D4LGny+YvFxVqPDN\n" +
//                    "4KfE2lkQRhfFPtTQD/hVuDr1TMvOXMRK70rdYZzUEnia5wFY2nCliEAzYt2R8fLV\n" +
//                    "8BoTfFuYhAoWoEEHdC6lCwHNhgQZnCv0XWVPGw7hfoKZbOGXQQ6xnpqIZtBwilbA\n" +
//                    "/92tvgIz9QmU5tySN7Wb80dVjYt22mGZ9R3ZLjeb0bJECXriifOnFoeQo+nWWoUF\n" +
//                    "AdM2UyL4ysv7L7GwRwvAjIUTzh463VRbgpQ+DQIDAQABAoIBAQDCmuAhL/mZdSNE\n" +
//                    "uXeeuLGeUQwTMiEF9BPkjS/cMOQtEmVfT4H+vtdco5wTyghKwpFe8bTsrlhIVnGi\n" +
//                    "aapecpNf9ziLFbpaqHdobbXSJf92eZVPJ/rcHfuffBe/Si276aQLwcGQd+drMQEz\n" +
//                    "jihs/kFcWx4roFU2UljUZ6+HCOCJWzBPDE0E7xBMenEQ5S/2bQgv2C//qTvgZB/+\n" +
//                    "p4Qp0bTFjBip/f6oXMMuoAWXWgtlVVD4gi47FpFtv3pzWyIqcWZuSJBmwCBl+uLp\n" +
//                    "xtFzhByyrq92xD6690FbRmZmc6my6ngnoQ/RYsXqZMP3UVqqKDjKYw3ZorKpGnIV\n" +
//                    "EQZcgnfdAoGBAPdrAUOH0TsIBi6ejEtZwUZNqb41ASGZstGXYdBHIgaeASQ9XfKX\n" +
//                    "jnHMNyi3TJWV+Gr8urS8ncZd7kZM/yf3nbYRQz67xp6NI451CGPTK9TBJaOG8qkJ\n" +
//                    "BTfW0vnN8hf49cIX6B3A7jMU0Y6lHh2hfd28KhOK+LIt/G3MPR7yjN93AoGBAORN\n" +
//                    "3nXkejtuWwyiPG6AyNkKfagVD8Ebzz6XImp7+IBhlxBGhX9dcGW6aoA/ZO6AEtnB\n" +
//                    "c0DuWMd2iaQhqaCuKLcv7wPWolAAI45BEUCuVBwOPUoIClIAlEvXejk/xRh599Nk\n" +
//                    "jkN3rbYCjf4lWrvATJpU3cS0+kJCorb9i4/kRNebAoGBAIjuiOmGDo98Nmewyfad\n" +
//                    "+zDcpLgnf7PH68bB4Jmz9Kny4rDa4db0eBqVKmX3ZIRZVv5nVzHWPf/eVpRD4ueM\n" +
//                    "zJVKYowiwp9Xf7lFHlqZMeXy69DgTAavflrUIzmr+HO6DtHYB1gIQgy1VfF7gvpl\n" +
//                    "OVLx49ujC1cZyl4+Lgmltb+rAoGAPzPrdRm2WQOZAytarYDLmYJU/RYvkVdAjWRL\n" +
//                    "q8z8I5Zguqm7RWI5vn/YAf8K0xfzoAL1BDG3tCh1vFFxY0EzllsXwSlHL/yJ21Ta\n" +
//                    "ENzx/f5XK0qEiOUOKNzTQkwMJVWGrSf2VmkoVu3oH0Z6EuYOjt8VseIkMfm+kAgq\n" +
//                    "/b/E6ycCgYEA9GaDqjVVQ+/N6pWQd+cpHFH/WDWSywGu7/+Zupq3ue5MvRdxJNsn\n" +
//                    "iZs0CSyqtYukUA7d2NoMPc66c8HGR6/T2pbBOljqqrq2zsoPnsvPSopz6WRHLcxY\n" +
//                    "xIDxKivOSLXx0AUxKVlsbjLnReouGVHrgPwTEGaCCfvgIe/59B6ELYA=\n" +
//                    "-----END RSA PRIVATE KEY-----\n" +
//                    "\n" +
//                    "</key>\n";
//
//            data = s.getBytes();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        ConfigParser cp = new ConfigParser();
//        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(data));
//        try {
//            cp.parseConfig(isr);
//            vpnProfile = cp.convertProfile();
//            vpnProfile.mName = mServer.getCountryLong();
//
////            if (filterAds) {
////                vpnProfile.mOverrideDNS = true;
////                vpnProfile.mDNS1 = "198.101.242.72";
////                vpnProfile.mDNS2 = "23.253.163.53";
////            }
//
//            ProfileManager.getInstance(this).addProfile(vpnProfile);
//        } catch (IOException | ConfigParser.ConfigParseError e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    private ServiceConnection mConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName className,
//                                       IBinder service) {
//            // We've bound to LocalService, cast the IBinder and get LocalService instance
//            OpenVPNService.LocalBinder binder = (OpenVPNService.LocalBinder) service;
//            mVPNService = binder.getService();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName arg0) {
//            mVPNService = null;
//        }
//    };
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unbindService(mConnection);
//    }
//}
