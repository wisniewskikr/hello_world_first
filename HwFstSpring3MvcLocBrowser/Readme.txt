CHANGE LANGUAGE IN CHROME BROWESER


1. Run Chrome


2. Open languagle section

Settings -> Show advanced settings... -> Languages -> Languages and spell-checker settings 


3. Choose language


4. Restart browser



--------------------------------------------------------------------------------------------------



LOCALIZATION

Topics connected with Spring 3 Mvc and localization:

I. LOCALIZATIN FROM BROWSER
II. CHANGE LOCALIZATION ON CODE SIDE
III. CHANGE LOCALIZATION ON WEB SIDE


I. LOCALIZATIN FROM BROWSER
If you want to take localization from browser, you can just use object Locale as method parameter.
This is done by default. Then you can take localized message from object MessageSource.
Spring 3 takes Locale from:
- browser;
- system: if there is no message properties for locale from browser
- file messages.properties: if ther is no message properties for locale from browser and system
For instance.

	---
	
	<bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
	  <property name="defaultEncoding" value="UTF-8"/>
	  <property name="basenames">
	    <list>
	      <value>classpath:messages</value>
	    </list>
	  </property>
	</bean>
	
	---
	
	@Autowired
    private MessageSource messageSource;

	@RequestMapping(value="/welcome/{userName}")
	public ModelAndView displayPage(
			...
			Locale loc,
			...){
				...
				String message = messageSource.getMessage("message.test", null, loc);
				...
	}
	
	---


II. CHANGE LOCALIZATION ON CODE SIDE
If you want to change localization on code side, you have to additonally declare object LocaleResolver.
You can set here default locale different then this in browser. You can also inject object LocaleResolver
in code and change locale.

	---
	
	<!-- 
 	 Resolvers enable to change locale on code side. 
 	 You can just inject resolver and change locale.
 	 You can also change default locale for different then in browser. 
 	 -->
	<!--  Possibilities: "SessionLocaleResolver", "CookieLocaleResolver", "AcceptHeaderLocaleResolver"  -->
	<bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver">
		<property name="defaultLocale" value="en" />
	</bean>
	
	---

	

III. CHANGE LOCALIZATION ON WEB SIDE
If you want to change localization on web side, you have have to additionally declare object LocaleChangeInterceptor.
It will look for parameter "lang" and change locale for its value.

	---
	
	<!-- 
	Interceptors enable to change locale on web side. 
	They are looking for parameter "lang" and change locale. 
	-->
	<mvc:interceptors>
 		<!-- This runs for all mappings -->
     	<bean  class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
     		<property name="paramName" value="lang" /> 
     	</bean>
	</mvc:interceptors>
	
	---
	