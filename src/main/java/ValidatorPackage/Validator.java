package ValidatorPackage;

import Exceptions.ValidationException;

public interface Validator<E> {
    void validate(E entity) throws ValidationException;
}
