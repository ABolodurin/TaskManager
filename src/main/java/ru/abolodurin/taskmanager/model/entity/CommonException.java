package ru.abolodurin.taskmanager.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class CommonException extends RuntimeException {
    private final String message;

}
