package com.vladislaviliev.airservers.resource

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:secrets.properties")
class Config