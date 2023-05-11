package com.kb.jjan.domain.bank.history;

import com.kb.jjan.domain.user.User;
import com.kb.jjan.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "SEQ_HISTORIES_GENERATOR",
        sequenceName ="SEQ_HISTORIES",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "histories")
public class History extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator="HISTORY_ID"
    )
    @Column(name = "history_id")
    private Long historyId;

    @ManyToOne
    @JoinColumn(name = "send_user", nullable = false)
    private User sendUser;

    @ManyToOne
    @JoinColumn(name = "received_user", nullable = false)
    private User receivedUser;

    @Column(length = 7, columnDefinition = "NUMBER(7)", nullable = false)
    private int price;

    @Column(name = "deal_msg", length = 20)
    private int dealMsg;
}
