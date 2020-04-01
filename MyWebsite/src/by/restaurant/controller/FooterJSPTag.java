package by.restaurant.controller;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class FooterJSPTag extends TagSupport {

    private String local;

    public void setLocal(String local) {
        this.local = local;
    }
    
    public String getLocal() {
		return local;
	}

	@Override
    public int doStartTag() throws JspException {
        try {
            String footerMsg;
            if (local.equalsIgnoreCase("ru") || local.equals("")) {
                footerMsg = "© Ресторан | Эвелина Саркисян";
            } else  {
                footerMsg = "© Restaurant | Evelina Sarkisyan";
            }
            JspWriter out = pageContext.getOut();
            out.write("<footer>");
            out.write(" <div class=\"footer-copyright text-center py-3\">");
            out.write(" <p class=\"text-muted\">" + footerMsg + "</p>");
            out.write("</div>");
            out.write("</footer>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
