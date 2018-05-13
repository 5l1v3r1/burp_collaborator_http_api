package burp;

import java.io.PrintWriter;

public class BurpExtender extends Thread implements IBurpExtender, IExtensionStateListener
{
	public String ExtenderName = "Collaborator HTTP API";
	public String github = "https://github.com/bit4woo/BCHA";
	public IBurpCollaboratorClientContext ccc;
	public IExtensionHelpers helpers;
	public PrintWriter stdout;//�����������Ҫ���ڴ������
	public CHTTPServer httpserver;

	@Override
	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
	{
		stdout = new PrintWriter(callbacks.getStdout(), true);
		stdout.println(ExtenderName);
		stdout.println(github);
		callbacks.setExtensionName(ExtenderName);
		callbacks.registerExtensionStateListener(this);
		ccc = callbacks.createBurpCollaboratorClientContext();
		helpers = callbacks.getHelpers();
		httpserver = new CHTTPServer(this);//!!!����this�����Ա�httpserver�п��Ե������ķ���������!!!!
		//stdout.println(this);
		httpserver.run();
		start();
	}

	@Override
	public void extensionUnloaded() {
		httpserver.exit();//ֹͣ�˿ڼ���
	}
}
