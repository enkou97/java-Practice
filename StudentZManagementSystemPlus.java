import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StudentZManagementSystemPlus {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();

        User testUser = new User("enkou97", "yh19970414", "620102199700000000", "13893000000");
        list.add(testUser);

        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作  1.登录 2.注册 3.忘记密码");
            Scanner sc = new Scanner(System.in);
            String chooseNumber = sc.next();

            switch (chooseNumber) {
                case "1": {
                    loginAccount(list); //登录
                    break;
                }

                case "2": {
                    enrollmentAccount(list); //注册
                    break;
                }

                case "3": {
                    forgotPassword(list); //忘记密码
                    break;
                }

                default:
                    System.out.println("没有当前选项");
                    return;
            }
        }
    }

    //注册
    public static void enrollmentAccount(ArrayList<User> list) {
        //1.用户名唯一，长度3-15位之间，只能是字母加数字的组合
        //2.密码键盘输入两次，两次都一致才可以注册
        //3.身份证号需要验证
        //验证要求：
        //长度为18//不能以0开头//前17位，必须都是数字//最后一位可以是数字，也可以是大写X或者小写x
        //4.手机号验证
        //验证要求：
        //长度为11位//不能以0开头//必须都是数字
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要注册的用户名");

        //注册用户名
        String inputName;
        while (true) {
            inputName = sc.next();
            boolean isUniqueName = containsName(list, inputName);
            boolean isCorrectName = isAlphaNumeric(inputName);

            if (!isUniqueName && isCorrectName && isCorrectLength(inputName)) {
                break;
            } else {
                System.out.println("输入的用户名存在数字和字母以外的字符，请重新输入");
            }

            //检验用户名是否唯一，应该单独判断
        }

        //密码
        String password;
        String confirmePassword;
        while (true) {
            System.out.println("请输入密码");
            password = sc.next();
            System.out.println("请再次输入密码");
            confirmePassword = sc.next();

            if (!password.equals(confirmePassword)) {
                System.out.println("两次密码不一致，请再次输入密码");
            } else {
                break;
            }
        }

        //身份证号验证
        String identityCards;
        while (true) {
            System.out.println("请输入您的身份证号");
            identityCards = sc.next();

            if (isCorrectIdentityCards(identityCards)) {
                break;
            } else {
                System.out.println("输入的身份证号不合法，请重新输入");
            }
        }

        //手机号验证
        String phoneNumber;
        while (true) {
            System.out.println("请输入您的手机号");
            phoneNumber = sc.next();

            if (isCorrectPhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println("输入的手机号不合法，请重新输入");
            }

        }

        User u1 = new User(inputName, password, identityCards, phoneNumber);
        list.add(u1);
    }

    //登录
    public static void loginAccount(ArrayList<User> list) {
        //键盘录入用户名 --> 是否和list里面的user用户名一致
        //键盘录入密码 --> 密码是否一致
        //键盘录入验证码 --> 长度为5//由4位大写或小写字母和1位数字组成，同一个字母可重复//数字可出现在任意位置
        //ex：aQa1K

        Scanner sc = new Scanner(System.in);

        String userName;
        while (true) {
            System.out.println("请输入用户名");
            userName = sc.next();
            if (isSameUserName(list, userName)) {
                break;
            } else {
                System.out.println("输入的用户名不一致，请重新输入");
            }
        }

        String passWord;
        while (true) {
            System.out.println("请输入密码");
            passWord = sc.next();
            if (isSamePassWord(list, passWord)) {
                break;
            } else {
                System.out.println("输入的密码不一致，请重新输入");
            }
        }

        String captcha;
        while (true) {
            String randomCaptcha = createCaptcha();
            System.out.println("请输入验证码" + randomCaptcha);
            captcha = sc.next();
            //验证码的比较是忽略大小写的
            if (randomCaptcha.equals(captcha)) {
                break;
            } else {
                System.out.println("输入的验证码不一致，请重新输入");
            }
        }

        StudentSystem ss = new StudentSystem();
        ss.StartStudentSystem();
    }

    //忘记密码
    public static void forgotPassword(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入忘记密码的用户名");
        String inputUserName = sc.next();
        int index = getUserIndex(list, inputUserName);

        if (index >= 0) {
            System.out.println("请输入身份证号码");
            String inputIdentityCards = sc.next();
            System.out.println("请输入手机号码");
            String inputPhoneNumber = sc.next();

            if (isSameIdentityCards(list, inputIdentityCards, index) && isSamePhoneNumber(list, inputPhoneNumber, index)) {
                System.out.println("请输入要修改的密码");
                String modifyPassword = sc.next();
                list.get(index).setPassword(modifyPassword);
            } else {
                System.out.println("账户信息不匹配，修改密码失败");
            }
        } else {
            System.out.println("用户名不存在，密码修改失败");
        }
    }

    public static boolean containsName(ArrayList<User> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            String listName = list.get(i).getName();
            if (name.equals(listName)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isAlphaNumeric(String str) {
        // 定义正则表达式，表示只包含字母和数字
        String regex = "^[a-zA-Z0-9]+$";

        // 使用正则表达式匹配字符串
        return Pattern.matches(regex, str);
    }

    public static boolean isCorrectLength(String str) {
        return str.length() >= 3 && str.length() <= 15;
    }

    public static boolean isNumberLength(String str, int number) {
        return str.length() == number;
    }

    public static boolean isZeroStart(String str) {
        return str.charAt(0) == '0';
    }

    public static boolean isSeventeenAllNumbers(String str) {
        char[] arr = str.toCharArray();
        for (char c : arr) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean lastValue(String str) {
        char[] arr = str.toCharArray();
        char last = arr[arr.length - 1];

        return Character.isDigit(last) || last == 'X' || last == 'x';
    }

    public static boolean isCorrectIdentityCards(String str) {
        return isNumberLength(str, 18) && !isZeroStart(str) && isSeventeenAllNumbers(str) && lastValue(str);
    }

    public static boolean isAllNumbers(String phoneNumber) {
        char[] arr = phoneNumber.toCharArray();
        boolean flag = true;

        for (char c : arr) {
            if (!Character.isDigit(c)) {
                flag = false;
            }
        }

        return flag;
    }

    public static boolean isCorrectPhoneNumber(String phoneNumber) {
        return isNumberLength(phoneNumber, 11) && !isZeroStart(phoneNumber) && isAllNumbers(phoneNumber);
    }

    public static boolean isSameUserName(ArrayList<User> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            User _user = list.get(i);
            if (name.equals(_user.getName())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isSamePassWord(ArrayList<User> list, String passWord) {
        for (int i = 0; i < list.size(); i++) {
            User _user = list.get(i);
            if (passWord.equals(_user.getPassword())) {
                return true;
            }
        }

        return false;
    }

    public static char[] generateAlphabet() {
        char[] alphabet = new char[52];
        int index = 0;

        // 添加大写字母
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet[index++] = c;
        }

        // 添加小写字母
        for (char c = 'a'; c <= 'z'; c++) {
            alphabet[index++] = c;
        }

        return alphabet;
    }

    public static String createCaptcha() {
        //键盘录入验证码 --> 长度为5//由4位大写或小写字母和1位数字组成，同一个字母可重复//数字可出现在任意位置
        //ex：aQa1K
        char[] alphabetArr = generateAlphabet();
        char[] numberArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Random r = new Random();
        StringBuilder alphabetStr = new StringBuilder();

        //添加字母
        for (int i = 0; i < 4; i++) {
            int randomIndex = r.nextInt(52);
            alphabetStr.append(alphabetArr[randomIndex]);
        }

        //添加数字
        int randomIndex2 = r.nextInt(10);
        char numberStr = numberArr[randomIndex2];
        alphabetStr.append(numberStr);

        //打乱内容
        for (int i = 0; i < alphabetStr.length(); i++) {
            int randomNumber = r.nextInt(alphabetStr.length());
            char temp = alphabetStr.charAt(i);
            // 将第randomNumber个字符放到第i个位置
            alphabetStr.setCharAt(i, alphabetStr.charAt(randomNumber));
            // 将之前保存的第i个字符放到第randomNumber个位置
            alphabetStr.setCharAt(randomNumber, temp);
        }

        return alphabetStr.toString();
    }

    public static int getUserIndex(ArrayList<User> list, String userName) {
        for (int i = 0; i < list.size(); i++) {
            String listUserName = list.get(i).getName();

            if (userName.equals(listUserName)) {
                return i;
            }
        }

        return -1;
    }

    public static boolean isSameIdentityCards(ArrayList<User> list, String inputUserName, int index) {
        String listUserIdentityCards = list.get(index).getIdentityCards();

        return inputUserName.equals(listUserIdentityCards);
    }

    public static boolean isSamePhoneNumber(ArrayList<User> list, String inputPhoneNumber, int index) {
        String listUserPhoneNumber = list.get(index).getPhoneNumber();

        return inputPhoneNumber.equals(listUserPhoneNumber);
    }

}