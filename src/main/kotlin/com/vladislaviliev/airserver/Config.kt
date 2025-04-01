package com.vladislaviliev.airserver

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:secrets.properties")
class Config