package az.unitech.development.exchange.error;

public enum ErrorCodes implements ErrorCode {

   UNSUPPORTED_CONVERT;

    @Override
    public String code() {
        return this.name();
    }

}
