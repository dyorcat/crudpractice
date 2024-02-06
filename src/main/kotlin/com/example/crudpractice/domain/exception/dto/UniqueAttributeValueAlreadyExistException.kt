package com.example.crudpractice.domain.exception.dto

data class UniqueAttributeValueAlreadyExistException(val attribute: String, val value: String): RuntimeException(
    "You are trying to insert value $value in $attribute, But, $value is already exist in $attribute"
)
