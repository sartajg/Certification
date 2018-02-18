package com.lift.util;

import java.util.Collections;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

public class DBUtil {

	public static Query createInClause(String place, Session session, StringBuilder builder, Set<?> input) {
		
		String expr = String.join(", ", Collections.nCopies(input.size(), "?"));
		int startIndx = builder.indexOf(place);
		builder.replace(startIndx, startIndx+place.length(), expr);
		
		Query query = session.createQuery(builder.toString());
		
		for (Object object : input) {
//			query.set
		}
		
		return null;
		/*Query query = session.createQuery(arg0);*/
	}
}
