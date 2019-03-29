package httpserver.itf.impl;

import java.io.IOException;
import java.util.HashMap;

import httpserver.itf.HttpResponse;
import httpserver.itf.HttpRicmlet;
import httpserver.itf.HttpRicmletRequest;
import httpserver.itf.HttpRicmletResponse;
import httpserver.itf.HttpSession;

public class HttpRicmletRequestImpl extends HttpRicmletRequest {

	HashMap<String, HttpRicmlet> hm;

	public HttpRicmletRequestImpl(HttpServer hs, String method, String ressname) throws IOException {
		super(hs, method, ressname);
		hm = new HashMap<String, HttpRicmlet>();
	}

	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getArg(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCookie(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void process(HttpResponse resp) throws Exception {
		
		String name = this.m_ressname.replace('/','.').replace(".ricmlets.", "");
		System.out.println(name);
		if (!hm.containsKey(name)) {
			hm.put(name, (HttpRicmlet) Class.forName(name).newInstance());
		}
		HttpRicmlet hr = hm.get(name);
		hr.doGet(this, (HttpRicmletResponse) resp);
		
		
	}

}
