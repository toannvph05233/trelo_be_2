package com.codegym.trello.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.*;

@Entity
@Table(name = "messenger")
@Data
public class MessengerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String messenger;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}

