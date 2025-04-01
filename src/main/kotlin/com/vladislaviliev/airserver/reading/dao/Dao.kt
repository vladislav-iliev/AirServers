package com.vladislaviliev.airserver.reading.dao

import com.vladislaviliev.airserver.reading.Reading
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface Dao : CrudRepository<Reading, Long>, ManualDao
