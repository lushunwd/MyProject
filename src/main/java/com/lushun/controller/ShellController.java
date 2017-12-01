package com.lushun.controller;

import com.lushun.common.RemoteShellExecutor;
import com.lushun.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShellController {
	@RequestMapping("/shell")
	public String shell () {
		try {
			backupAndRestoreData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	private void backupAndRestoreData() throws Exception {
		//1. 调用远程Shell脚本，对生产库中数据进行导出和导入备份。
		RemoteShellExecutor executor = new RemoteShellExecutor("192.168.202.195", "root", "TEST@lushun.com");
		// 执行myTest.sh 参数为java Know dummy
		System.out.println(executor.exec("/tmp/myTest.sh"));
	}

}
