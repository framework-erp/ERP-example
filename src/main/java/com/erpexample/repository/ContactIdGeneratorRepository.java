package com.erpexample.repository;

import dml.common.repository.CommonSingletonRepository;
import dml.id.entity.IdGenerator;

public interface ContactIdGeneratorRepository extends CommonSingletonRepository<IdGenerator<Long>> {
}
