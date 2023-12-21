package hello.hellospring.domain

import jakarta.persistence.*

@Entity
class Member(name:String? = null) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // DB가 알아서 생성해주는 것을 IDENTITY라고 함
    var id: Long? = null

    @Column()
    var name : String? = name


}