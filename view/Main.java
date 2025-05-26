//package view;
//
//
//import controller.ControllerDangNhap;
//import repository.UserRepository;
//import view.ViewDangNhap;
//
//import javax.swing.SwingUtilities;
//
//public class Main {
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            UserRepository repo = new UserRepository();      // Model
//            ViewDangNhap view = new ViewDangNhap(repo);      // View
//            new ControllerDangNhap(view, repo);              // Controller
//            view.setVisible(true);
//        });
//    }
//}
