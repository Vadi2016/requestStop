/**
 * 
 */
package ru.requeststop.rs;

import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.ws.rs.core.Application;

import ru.funsys.avalanche.Avalanche;

/**
 * <p>����� ����������� ������������� REST �������� � �������� Jersey (JAX-RS 2.0)
 * <b>{@code org.glassfish.jersey.servlet.ServletContainer}</b>, �� web.xml, ��������
 * <b>{@code javax.ws.rs.Application}</b>. ������:</p>
 * <pre>
 * &lt;servlet>
 *   &lt;servlet-name>jersey-serlvet&lt;/servlet-name>
 *   &lt;servlet-class>org.glassfish.jersey.servlet.ServletContainer&lt;/servlet-class>
 *     &lt;init-param>
 *       &lt;param-name>javax.ws.rs.Application&lt;/param-name>
 *       &lt;param-value>ru.transset.ad.mp.rs.ApplicationMP&lt;/param-value>
 *     &lt;/init-param>
 *     &lt;init-param>
 *       &lt;param-name>jersey.config.server.provider.packages&lt;/param-name>
 *       &lt;param-value>ru.transset.app.mp.service&lt;/param-value>
 *     &lt;/init-param>
 *   &lt;load-on-startup>2&lt;/load-on-startup>
 * &lt;/servlet>
 * </pre>
 * <p>��� ������ ������������� �������� �������������� ��� "singleton" - ��������� �
 * ����� ����������. ������ ��������� ������ ������������ ��������� ������ �
 * ������������ �������. ��� ����������� ����������� ������������� ����� ������
 * �������������� �������. �������� ����, ������������� � ����� ������, ����� ����
 * �������������� ���������, ��������������� � ������ ������.</p>
 * <p>
 * ��� ����������� ������ ���������� ������� REST ��������, ���������� ������� ��������� ���
 * ������� ������,  ������������ �������� <b>{@code jersey.config.server.provider.packages}</b>.       
 * 
 * @author ������� ���������
 *
 */
public class ApplicationTrans extends Application {

    private Set<Object> singletons;
    
	/**
	 * � ������������ ������ ���������� ������ �� ��������� ������ Avalanche �� JNDI �����,
	 * ������� ������������ � ������������ ��������� �������. ������:
	 * <pre>
	 * &lt;Context docBase="trans" path="/trans" reloadable="true" source="org.eclipse.jst.jee.server:mp">
	 *   &lt;Resource auth="Container" factory="org.apache.naming.factory.BeanFactory"
	 *             name="avalanche/trans"
	 *             type="ru.funsys.avalanche.Avalanche"/>
	 * &lt;/Context>
	 * </pre> 
	 * ��� WEB ������� Jakarta-Tomcat ������������ ��� ��������� JNDI <b>{@code java:comp/env}</b>,
	 * ������ JNDI ��� - <b>{@code java:comp/env/avalanche/trans}</b> 
	 * 
	 * @throws Exception
	 */
	public ApplicationTrans() throws Exception {
		// �������� �������� �������� JNDI
		Context initContext = new InitialContext();
		// �������� ������ �� ����� Avalanche �� ����� JNDI
		Avalanche avalanche = (Avalanche) initContext.lookup(Avalanche.getJndiContext() + '/' + "avalanche/trans");
		// �������� ������ �������, ������������� REST ��������
		singletons = avalanche.getSingletons();
	}

	@Override
	public Set<Object> getSingletons() {
    		if (singletons == null) return super.getSingletons();
		else return singletons;
	}
    
}
