package anatoldevelopers.by.validator.validator;

import anatoldevelopers.by.validator.validator.date.DateAfterNowValidator;
import anatoldevelopers.by.validator.validator.date.DateBeforeNowValidator;
import anatoldevelopers.by.validator.validator.date.DateNowOrAfterValidator;
import anatoldevelopers.by.validator.validator.date.MaxAgeValidator;
import anatoldevelopers.by.validator.validator.date.MaxDiffDaysValidator;
import anatoldevelopers.by.validator.validator.date.MinAgeValidator;

public enum Validators {

    REG_EXP("regExp", new RegExpValidator()),
    REQUIRED("required", new RequiredValidator()),
    ENUM("enum", new EnumValidator()),
    MIN("min", new MinValidator()),
    MAX("max", new MaxValidator()),
    LENGTH("length", new LengthValidator()),
    MAX_LENGTH("maxLength", new MaxLengthValidator()),
    DATE_AFTER_NOW("dateAfterNow", new DateAfterNowValidator()),
    DATE_NOW_OR_AFTER("dateNowOrAfter", new DateNowOrAfterValidator()),
    DATE_BEFORE_NOW("dateBeforeNow", new DateBeforeNowValidator()),
    MAX_AGE_VALIDATOR("maxAge", new MaxAgeValidator()),
    MIN_AGE_VALIDATOR("minAge", new MinAgeValidator()),
    MAX_DIFF_DAYS_VALIDATOR("maxDiffDays", new MaxDiffDaysValidator());

    private final String keyword;
    private final Validator validator;

    Validators(String keyword, Validator validator) {
        this.keyword = keyword;
        this.validator = validator;
    }

    public String getKeyword() {
        return keyword;
    }

    public Validator getValidator() {
        return validator;
    }
}
