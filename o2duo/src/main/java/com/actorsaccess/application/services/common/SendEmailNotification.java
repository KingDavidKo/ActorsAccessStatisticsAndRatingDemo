package com.actorsaccess.application.services.common;

import com.actorsaccess.application.config.AAConfig;
import com.actorsaccess.application.system.AAUtil;

public class SendEmailNotification {

	static public void sendEmailMessage(String from, Object to, String subj, String msg, String mime, String encoding,
			Object bcc, String[] attachInfoArr, String user, String psw, String host, Object port, Object secure) throws Exception {

		boolean ssl = false;
		boolean usebcc = false;

		if (bcc instanceof Boolean)
			usebcc = ((Boolean) bcc).booleanValue();
		if (secure instanceof Boolean) {
			ssl = ((Boolean) secure).booleanValue();
		} else if (secure instanceof String) {
			ssl = Boolean.valueOf((String)secure);
		}
		
		int nport = -1;
		if (port != null && port.toString().length() > 0)
			nport = AAUtil.stringToPositiveInt(port.toString(), true);
		if (encoding == null)
			encoding = AAConfig.DEFAULT_ENCODING;
		try {
			SMTPServiceProvider.transmit(from, to, subj, msg, host, (String) mime, (String) user, (String) psw, nport,
					ssl, null, encoding, usebcc);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
