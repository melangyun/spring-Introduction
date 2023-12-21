package hello.hellospring.repository

import hello.hellospring.domain.Member
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import javax.sql.DataSource

class JdbcTemplateMemberRepository(dataSource: DataSource) : MemberRepository {

    private val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)

    override fun save(member: Member): Member {
        val jdbcInsert = SimpleJdbcInsert(jdbcTemplate)
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id")

        val parameters = HashMap<String, String?>()
        parameters["name"] = member.name

        val key = jdbcInsert.executeAndReturnKey(MapSqlParameterSource(parameters))
        member.id = key.toLong()
        return member
    }

    override fun findById(id: Long?): Member? {
        return jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id)
            .firstOrNull()
    }

    override fun findByName(name: String?): Member? {
        return jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name)
            .firstOrNull()
    }

    override fun findAll(): List<Member> {
        return jdbcTemplate.query("select * from member", memberRowMapper())
    }

    private fun memberRowMapper(): RowMapper<Member> {
        return RowMapper<Member> { rs, _ ->
            val member = Member()
            member.id = rs.getLong("id")
            member.name = rs.getString("name")
            member
        }
    }

}