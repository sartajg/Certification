package com.lift.util;

import org.hibernate.Session;

public interface HibernateUtil {

	abstract Session getSession();
}
