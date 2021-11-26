package com.mingo.butler.domain;

import io.norberg.automatter.AutoMatter;

import java.time.LocalDate;

@AutoMatter
public interface FileDetails {

    String ticketId();

    String messageId();

    String url();

    LocalDate createdDate();
}
