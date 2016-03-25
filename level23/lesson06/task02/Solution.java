package com.javarush.test.level23.lesson06.task02;

/* Рефакторинг
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
*/
public class Solution {

    public class ServerNotAccessibleException extends Exception {
        public ServerNotAccessibleException() {
            super(Constants.SINAFN);
        }

        public ServerNotAccessibleException(Throwable cause) {
            super(Constants.SINAFN, cause);
        }
    }

    public class UnauthorizedUserException extends Exception {
        public UnauthorizedUserException() {
            super(Constants.UINA);
        }

        public UnauthorizedUserException(Throwable cause) {
            super(Constants.UINA, cause);
        }
    }

    public class BannedUserException extends Exception {
        public BannedUserException() {
            super(Constants.UIB);
        }

        public BannedUserException(Throwable cause) {
            super(Constants.UIB, cause);
        }
    }

    public class RestrictionException extends Exception {
        public RestrictionException() {
            super(Constants.AID);
        }

        public RestrictionException(Throwable cause) {
            super(Constants.AID, cause);
        }
    }

    public static final class Constants {
        public static final String SINAFN = "Server is not accessible for now.";
        public static final String UINA = "User is not authorized.";
        public static final String UIB = "User is banned.";
        public static final String AID = "Access is denied.";
    }
}
