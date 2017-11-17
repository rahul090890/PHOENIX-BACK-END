package com.concretepage.email.builder;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class BaseMailContentBuilder implements IMailContentBuilder{

	private TemplateEngine templateEngine;

	@Autowired
	public BaseMailContentBuilder(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}
	
	@Override
	public String build(Map<String,String> dynamicFields, String mailTemplate) {
        Context context = new Context();
        for(Map.Entry<String, String> entry :dynamicFields.entrySet()) {
        	System.out.println(entry.getKey() + ":" + entry.getValue() );
        	 context.setVariable(entry.getKey(),entry.getValue());	
        }
       
        return templateEngine.process(mailTemplate, context);
    }
	
}
