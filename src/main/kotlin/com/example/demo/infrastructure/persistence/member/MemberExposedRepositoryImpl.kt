package com.example.demo.infrastructure.persistence.member

import com.example.demo.config.persistence.ExposedDataSourceConfig
import com.example.demo.core.member.domain.Member
import com.example.demo.core.member.domain.Members
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class MemberExposedRepositoryImpl(
    private val exposedDataSourceConfig: ExposedDataSourceConfig,
) : MemberExposedRepository {
    // Jpa 엔티티를 사용하는 것이 마음에 들지 않지만, 우선은 Jpa 엔티티를 파라미터로 받아서 처리
    override fun saveAll(members: List<Member>) {
        transaction(exposedDataSourceConfig.write()) {
            Members.batchInsert(
                data = members,
                ignore = false,
                shouldReturnGeneratedValues = false,
            ) {
                this[Members.name] = it.name
            }
        }
    }
}
