package fr.iotqvt.master.infra.db;

import com.jcabi.aspects.Immutable;

@Immutable
public interface IOTs {
	Iterable<IOT> iterate();

	IOT add(String id);
}