package com.ddetyuk.connection;

import java.util.HashMap;

public abstract class AmfAction implements Action{

	public AmfAction(){
		headers.put("Host", "n1-mhouse-vk.ilikegames.ru");
		headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:9.0.1) Gecko/20100101 Firefox/9.0.1 FirePHP/0.6");
		headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.put("Accept-Language", "ru-ru,ru;q=0.8,en-us;q=0.5,en;q=0.3");
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Accept-Charset", "windows-1251,utf-8;q=0.7,*;q=0.7");
		headers.put("Connection", "keep-alive");
		headers.put("x-insight", "activate");
		headers.put("Referer","http://cs304205.vkontakte.ru/[[IMPORT]]/cs304904.vkontakte.ru/u104048413/c4469458f209d6.zip?1288194647");
		headers.put("Content-type", "application/x-www-form-urlencoded");
	}
	
	@Override
	public HashMap<String, String> getHeaders() {
		return headers;
	}

	@Override
	public String getMathod() {
		return "POST";
	}

	@Override
	public String getUrl() {
		return "http://n1-mhouse-vk.ilikegames.ru/request.php";
	}
	
}
