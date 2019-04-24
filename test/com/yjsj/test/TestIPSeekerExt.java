package com.yjsj.test;

import java.util.List;

import com.yjsj.etl.util.IPSeekerExt;
import com.yjsj.etl.util.IPSeekerExt.RegionInfo;


public class TestIPSeekerExt {
	public static void main(String[] args) {
		IPSeekerExt ipSeekerExt = new IPSeekerExt();
		RegionInfo info = ipSeekerExt.analyticIp("114.114.114.114");
		System.out.println(info);

		List<String> ips = ipSeekerExt.getAllIp();
		for (String ip : ips) {
			System.out.println(ipSeekerExt.analyticIp(ip));
		}
	}
}
