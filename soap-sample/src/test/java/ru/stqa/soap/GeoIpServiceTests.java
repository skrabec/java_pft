package ru.stqa.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("82.142.125.250");
    System.out.println(geoIP);
  }
}
