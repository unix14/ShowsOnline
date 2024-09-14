package com.triPCups.blogs.base.repos.user_name_generator

import java.util.Date
import kotlin.random.Random

class RandNumberGeneratorImpl : RandomGenerator {

    override fun generate(): String = Random.nextInt().toString().plus(Date().time)
}