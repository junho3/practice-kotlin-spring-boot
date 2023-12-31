package com.example.demo.infrastructure.persistence.member

import com.example.demo.core.member.domain.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MemberRepository {
    fun findAll(pageable: Pageable): Page<Member>
}
