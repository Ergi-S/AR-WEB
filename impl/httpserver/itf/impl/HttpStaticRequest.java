package httpserver.itf.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import httpserver.itf.HttpRequest;
import httpserver.itf.HttpResponse;

public class HttpStaticRequest extends HttpRequest {
	static final int BUF_SZ = 1024;
	static final String DEFAULT_FILE = "index.html";
	
	public HttpStaticRequest(HttpServer hs, String method, String ressname) throws IOException {
		super(hs, method, ressname);
	}
	
	public void process(HttpResponse resp) throws Exception {
		File resfile = new File(m_hs.getFolder()+this.m_ressname);
		if(!resfile.canRead()) { //Permet de tester si le fichier existe et peut être lu
			resp.setReplyError(404, "File not found");
		} else {
			resp.setReplyOk();
			resp.setContentLength((int)resfile.length());
			resp.setContentType(getContentType(this.m_ressname));
			PrintStream ps = resp.beginBody();
			FileInputStream fis = new FileInputStream(resfile);
			int nread = 0;
			int flength = (int) resfile.length();
			byte[] payload = new byte[flength];
			int num = 0;
			while(nread < flength) { //récupération du payload du fichier
				num = fis.read(payload,nread,flength-nread);
				if (num == -1) {
					break;
				}
				nread += num;
			}
			ps.write(payload);
			
			
		}
		
	}

}
