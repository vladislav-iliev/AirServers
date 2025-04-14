package com.vladislaviliev.airservers.resource.reading.dao

import com.vladislaviliev.airservers.resource.reading.Reading
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface Dao : CrudRepository<Reading, Long>, ManualDao
