package com.svcg.StockCustom.component;

import java.io.IOException;

public interface MailAdapter {

	void sendMail(String to, String subject, String body) throws IOException;
	void sendMailHTML(String to, String subject, String body) throws IOException;

}
