package com.concretepage.email.builder;

import java.util.Map;

public interface IMailContentBuilder {

	public String build(Map<String,String> dynamicFields, String mailTemplate);	
}
