package fr.iotqvt.master.infra.db;

import com.jcabi.aspects.Immutable;

@Immutable
interface IOTs {
  Iterable<IOT> iterate();
  IOT add(String id);
}