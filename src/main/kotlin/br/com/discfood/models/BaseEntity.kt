package br.com.discfood.models

import lombok.EqualsAndHashCode
import lombok.Getter
import java.io.Serializable
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
abstract class BaseEntity : Serializable {

    @Id
    @EqualsAndHashCode.Include
    open var id: Long = System.nanoTime()

}

