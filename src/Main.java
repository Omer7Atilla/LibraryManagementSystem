import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Main implements ActionListener{
	static String[] menu_options = {"Add a new book", "Add a new Online Article", "Create a member account",
			"Check out a book", "Return a book", "Add access to a new online article",
			"End an online article access", "Display all accounts", "Display library database",
			"List accounts with overdue payments", "Exit"};
	
	static JList<String> menu_list = new JList<String>(menu_options);
	static JFrame frame = new JFrame("Library Management System");
	static JButton ok_button = new JButton("OK");
	
	public static void main(String[] args) throws ISBNMismatchException, NotValidDateException{
		JPanel panel = new JPanel();
		
		
		
		panel.setBackground(Color.WHITE);
		frame.setSize(300,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		panel.add(menu_list);
		
		int button_width = 150;
		int button_height = 30;
		
		int x = (frame.getWidth() - button_width)/2;
		int y = frame.getHeight() - button_height - 30;
		
		ok_button.setPreferredSize(new Dimension(frame.getWidth(), 50));
		ok_button.addActionListener(new Main());
		panel.add(ok_button, BorderLayout.SOUTH);
		
		frame.add(panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String selected_value = menu_list.getSelectedValue();
		if (selected_value == menu_options[0] && e.getSource() == ok_button) {//"Add a new book"
				JFrame frame_option_1 = new JFrame("Add New Book");
				JPanel panel_option_1 = new JPanel();
				
				panel_option_1.setLayout(null);
				
				frame_option_1.setSize(1000,800);
				
				frame_option_1.setLayout(null);
				
				panel_option_1.setBackground(Color.WHITE);
				
				JLabel book_name = new JLabel("Book Name:");
				JTextField book_name_input = new JTextField();

				JLabel isbn_number = new JLabel("ISBN:");
				JTextField isbn_number_input = new JTextField();
				
				String isbn = isbn_number_input.getText();
				
				JLabel price_value = new JLabel("Price:");
				JTextField price_value_input = new JTextField();

				JButton submit_button = new JButton("Submit");
				
				frame_option_1.add(panel_option_1);
				panel_option_1.setBounds(0,0,frame_option_1.getWidth(),frame_option_1.getHeight());
				
				panel_option_1.add(book_name);
				book_name.setBounds(20, 30, 120, 30);
				panel_option_1.add(book_name_input);
				book_name_input.setBounds(150, 30, 250, 30);
				
				panel_option_1.add(isbn_number);
				isbn_number.setBounds(20, 80, 120, 30);
				panel_option_1.add(isbn_number_input);
				isbn_number_input.setBounds(150, 80, 250, 30);
				
				panel_option_1.add(price_value);
				price_value.setBounds(20, 130, 120, 30);
				panel_option_1.add(price_value_input);
				price_value_input.setBounds(150, 130, 250, 30);
				
				panel_option_1.add(submit_button);
				submit_button.setBounds(200, 180, 100, 30);
				
				submit_button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							String book_name = book_name_input.getText();
							String isbn = isbn_number_input.getText();
							String priceText = price_value_input.getText();
							
							if(book_name.isEmpty() || isbn.isEmpty() || priceText.isEmpty()) {
								JOptionPane.showMessageDialog(frame_option_1, "Please fill all the fields"
										, "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							Book.authenticateISBN(isbn);
							
							double price = Double.parseDouble(priceText);
							
							Date date = new Date(25,5,2025);//Default date. Date of the homework given
							
							Book book = new Book(book_name, isbn, date, price);
							Book.bookArray.add(book);
						}catch(ISBNMismatchException ex) {
							JOptionPane.showMessageDialog(frame_option_1, "Invalid ISBN: "+ex.getMessage(),
									"Error",JOptionPane.ERROR_MESSAGE);
						}catch(NumberFormatException exc) {
							JOptionPane.showMessageDialog(frame_option_1, "Please enter a valid price",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				
				frame_option_1.setVisible(true);
		}
		else if (selected_value == menu_options[1] && e.getSource() == ok_button) {//"Add a new Online Article"
			JFrame frame_option_2 = new JFrame("Add New Online Article");
			JPanel panel_option_2 = new JPanel();
			
			panel_option_2.setLayout(null);
			
			frame_option_2.setSize(1000,800);
			
			frame_option_2.setLayout(null);
			
			panel_option_2.setBackground(Color.WHITE);
			
			JLabel article_name = new JLabel("Online Article Name:");
			JTextField article_name_input = new JTextField();

			JLabel doi_number = new JLabel("DOI:");
			JTextField doi_number_input = new JTextField();
			
			String doi = doi_number_input.getText();
			
			JLabel publisher = new JLabel("Publisher:");
			JTextField publisher_input = new JTextField();

			JButton submit_button = new JButton("Submit");
			
			frame_option_2.add(panel_option_2);
			panel_option_2.setBounds(0,0,frame_option_2.getWidth(),frame_option_2.getHeight());
			
			panel_option_2.add(article_name);
			article_name.setBounds(20, 30, 120, 30);
			panel_option_2.add(article_name_input);
			article_name_input.setBounds(150, 30, 250, 30);
			
			panel_option_2.add(doi_number);
			doi_number.setBounds(20, 80, 120, 30);
			panel_option_2.add(doi_number_input);
			doi_number_input.setBounds(150, 80, 250, 30);
			
			panel_option_2.add(publisher);
			publisher.setBounds(20, 130, 120, 30);
			panel_option_2.add(publisher_input);
			publisher_input.setBounds(150, 130, 250, 30);
			
			panel_option_2.add(submit_button);
			submit_button.setBounds(200, 180, 100, 30);
			
			submit_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String article_name = article_name_input.getText();
						String doi = doi_number_input.getText();
						String priceText = publisher_input.getText();
						
						if(article_name.isEmpty() || doi.isEmpty() || priceText.isEmpty()) {
							JOptionPane.showMessageDialog(frame_option_2, "Please fill all the fields"
									, "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						OnlineArticle online_article = new OnlineArticle(article_name, doi, priceText);
						double price = online_article.calculateCost();
						OnlineArticle.articleArray.add(online_article);
					}catch(NumberFormatException exc) {
						JOptionPane.showMessageDialog(frame_option_2, "Please enter a valid price",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			frame_option_2.setVisible(true);
		}
		else if (selected_value == menu_options[2] && e.getSource() == ok_button) {
			JFrame frame_option_3 = new JFrame("Create Member Account");
			JPanel panel_option_3 = new JPanel();
			
			panel_option_3.setLayout(null);
			frame_option_3.setSize(1000,800);
			
			frame_option_3.setLayout(null);
			panel_option_3.setBackground(Color.WHITE);
			
			String[] mem_types = {"Regular Member", "Student Member", "Academic Member"};
			
			JLabel member_sel = new JLabel("Select Member Type:");
			
			JComboBox<String> member_select = new JComboBox<String>(mem_types);
			
			JLabel member_name = new JLabel("Name:");
			
			JTextField name_field = new JTextField();
			
			JLabel member_id = new JLabel("ID:");
			
			JTextField id_field = new JTextField();
			
			JButton submit_button = new JButton("Submit");
			
			frame_option_3.add(panel_option_3);
			panel_option_3.setBounds(0,0,frame_option_3.getWidth(),frame_option_3.getHeight());
			
			panel_option_3.add(member_sel);
			member_sel.setBounds(20, 30, 120, 30);
			panel_option_3.add(member_select);
			member_select.setBounds(150, 30, 250, 30);
			
			panel_option_3.add(member_name);
			member_name.setBounds(20, 80, 120, 30);
			panel_option_3.add(name_field);
			name_field.setBounds(150, 80, 250, 30);
			
			panel_option_3.add(member_id);
			member_id.setBounds(20, 130, 120, 30);
			panel_option_3.add(id_field);
			id_field.setBounds(150, 130, 250, 30);
			
			panel_option_3.add(submit_button);
			submit_button.setBounds(200, 180, 100, 30);
			
			submit_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String selected_idx = (String) member_select.getSelectedItem();
					String name = name_field.getText();
					int id = Integer.parseInt(id_field.getText());
					
					if(name.isEmpty() || id_field.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frame_option_3, "Please fill all the fields"
								, "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					RegularMember new_member = new RegularMember(name, id);
					RegularMember.memberArray.add(new_member);
				}
			});

			frame_option_3.setVisible(true);
			
		}
		else if (selected_value == menu_options[3] && e.getSource() == ok_button) {
			JFrame frame_option_4 = new JFrame("Check Out a Book");
			JPanel panel_option_4 = new JPanel();
			
			panel_option_4.setLayout(null);
			panel_option_4.setBackground(Color.WHITE);
			
			frame_option_4.setSize(1000,800);
			frame_option_4.setLayout(null);
			
			JLabel id_label = new JLabel("Member ID:");
			JTextField id_field = new JTextField();
			
			JLabel book_isbn_label = new JLabel("Book ISBN:");
			JTextField book_isbn_field = new JTextField();
			
			JLabel due_year_label = new JLabel("Due Year(YYYY):");
			JTextField due_year_field = new JTextField();
			
			JLabel due_month_label = new JLabel("Due Month(MM):");
			JTextField due_month_field = new JTextField();
			
			JLabel due_day_label = new JLabel("Due Day(DD):");
			JTextField due_day_field = new JTextField();
			
			JButton submit_button = new JButton("Submit");
			
			frame_option_4.add(panel_option_4);
			panel_option_4.setBounds(0,0,frame_option_4.getWidth(),frame_option_4.getHeight());
			
			panel_option_4.add(id_label);
			id_label.setBounds(20, 30, 120, 30);
			panel_option_4.add(id_field);
			id_field.setBounds(150, 30, 250, 30);
			
			panel_option_4.add(book_isbn_label);
			book_isbn_label.setBounds(20, 80, 120, 30);
			panel_option_4.add(book_isbn_field);
			book_isbn_field.setBounds(150, 80, 250, 30);
			
			panel_option_4.add(due_year_label);
			due_year_label.setBounds(20, 130, 120, 30);
			panel_option_4.add(due_year_field);
			due_year_field.setBounds(150, 130, 250, 30);
			
			panel_option_4.add(due_month_label);
			due_month_label.setBounds(20, 180, 120, 30);
			panel_option_4.add(due_month_field);
			due_month_field.setBounds(150, 180, 250, 30);
			
			panel_option_4.add(due_day_label);
			due_day_label.setBounds(20, 230, 120, 30);
			panel_option_4.add(due_day_field);
			due_day_field.setBounds(150, 230, 250, 30);
			
			panel_option_4.add(submit_button);
			submit_button.setBounds(200, 280, 100, 30);
			
			submit_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int id = Integer.parseInt(id_field.getText());
					String isbn = book_isbn_field.getText();
					int year = Integer.parseInt(due_year_field.getText());
					int month = Integer.parseInt(due_month_field.getText());
					int day = Integer.parseInt(due_day_field.getText());
					
					if(id_field.getText().isEmpty() || isbn.isEmpty() || due_year_field.getText().isEmpty()
							|| due_month_field.getText().isEmpty() || due_day_field.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frame_option_4, "Please fill all the fields"
								, "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					else {
						boolean is_book_found = false;
						Book target_book = null;
						for (Book book : Book.bookArray) {
							if (isbn.equals(book.ISBN)) {
								is_book_found = true;
								target_book = book;
								break;
							}
						}
						boolean is_user_found = false;
						RegularMember target_member = null;
						for (RegularMember member : RegularMember.memberArray) {
							if (id == member.id) {
								is_user_found = true;
								target_member = member;
								break;
							}
						}
						if (is_book_found && is_user_found) {
							target_member.addBook(target_book);
							JOptionPane.showMessageDialog(null, "Book '"+target_book.nameOfBook+"'"
									+ " (ISBN:"+target_book.ISBN+") checked out by "+
									target_member.name);
						}
					}
				}
			});

			frame_option_4.setVisible(true);
		}
		else if (selected_value == menu_options[4] && e.getSource() == ok_button) {
			
			JFrame frame_option_5 = new JFrame("Return a Book");
			JPanel panel_option_5 = new JPanel();
			
			panel_option_5.setLayout(null);
			panel_option_5.setBackground(Color.WHITE);
			
			frame_option_5.setSize(1000,800);
			frame_option_5.setLayout(null);
			
			JLabel id_label = new JLabel("Member ID:");
			JTextField id_field = new JTextField();
			
			JLabel isbn_label = new JLabel("Book ISBN:");
			JTextField isbn_field = new JTextField();
			
			JButton submit_button = new JButton("Submit");
			
			frame_option_5.add(panel_option_5);
			panel_option_5.setBounds(0,0,frame_option_5.getWidth(),frame_option_5.getHeight());
			
			panel_option_5.add(id_label);
			id_label.setBounds(20, 30, 120, 30);
			panel_option_5.add(id_field);
			id_field.setBounds(150, 30, 250, 30);
			
			panel_option_5.add(isbn_label);
			isbn_label.setBounds(20, 80, 120, 30);
			panel_option_5.add(isbn_field);
			isbn_field.setBounds(150, 80, 250, 30);
			
			panel_option_5.add(submit_button);
			submit_button.setBounds(200, 130, 100, 30);
			
			submit_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int id = Integer.parseInt(id_field.getText());
					String isbn = isbn_field.getText();
					
					if(id_field.getText().isEmpty() || isbn.isEmpty()) {
						JOptionPane.showMessageDialog(frame_option_5, "Please fill all the fields"
								, "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					else {
						boolean is_book_found = false;
						Book target_book = null;
						for (Book book : Book.bookArray) {
							if (isbn.equals(book.ISBN)) {
								is_book_found = true;
								target_book = book;
								break;
							}
						}
						boolean is_user_found = false;
						RegularMember target_member = null;
						for (RegularMember member : RegularMember.memberArray) {
							if (id == member.id) {
								is_user_found = true;
								target_member = member;
								break;
							}
						}
						if (is_book_found && is_user_found) {
							target_member.returnBook(target_book.ISBN);
							JOptionPane.showMessageDialog(null, "Book '"+target_book.nameOfBook+"'"
									+ " (ISBN:"+target_book.ISBN+") returned by "+
									target_member.name);
						}
					}
				}
			});
			frame_option_5.setVisible(true);
		}
		else if (selected_value == menu_options[5] && e.getSource() == ok_button) {
			JFrame frame_option_6 = new JFrame("Add Access to a New Online Article");
			JPanel panel_option_6 = new JPanel();
			
			panel_option_6.setLayout(null);
			panel_option_6.setBackground(Color.WHITE);
			
			frame_option_6.setSize(1000,800);
			frame_option_6.setLayout(null);
			
			JLabel id_label = new JLabel("Member ID:");
			JTextField id_field = new JTextField();
			
			JLabel article_doi_label = new JLabel("DOI Number:");
			JTextField article_doi_field = new JTextField();
			
			JButton submit_button = new JButton("Submit");
			
			frame_option_6.add(panel_option_6);
			panel_option_6.setBounds(0,0,frame_option_6.getWidth(),frame_option_6.getHeight());
			
			panel_option_6.add(id_label);
			id_label.setBounds(20, 30, 120, 30);
			panel_option_6.add(id_field);
			id_field.setBounds(150, 30, 250, 30);
			
			panel_option_6.add(article_doi_label);
			article_doi_label.setBounds(20, 80, 120, 30);
			panel_option_6.add(article_doi_field);
			article_doi_field.setBounds(150, 80, 250, 30);
			
			panel_option_6.add(submit_button);
			submit_button.setBounds(200, 130, 100, 30);
			
			submit_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int id = Integer.parseInt(id_field.getText());
					String doi = article_doi_field.getText();
					
					if(id_field.getText().isEmpty() || doi.isEmpty()) {
						JOptionPane.showMessageDialog(frame_option_6, "Please fill all the fields"
								, "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					else {
						boolean is_article_found = false;
						OnlineArticle target_article = null;
						for (OnlineArticle article : OnlineArticle.articleArray) {
							if (doi.equals(article.DOI)) {
								is_article_found = true;
								target_article = article;
								break;
							}
						}
						boolean is_user_found = false;
						RegularMember target_member = null;
						for (RegularMember member : RegularMember.memberArray) {
							if (id == member.id) {
								is_user_found = true;
								target_member = member;
								break;
							}
						}
						if (is_article_found && is_user_found) {
							target_member.addOA(target_article);
							JOptionPane.showMessageDialog(null, "Book '"+target_article.nameOfArticle+"'"
									+ " (DOI:"+target_article.DOI+") checked out by "+
									target_member.name);
						}
					}
				}
			});

			frame_option_6.setVisible(true);
		}
		else if (selected_value == menu_options[6] && e.getSource() == ok_button) {
			JFrame frame_option_7 = new JFrame("End an online article access");
			JPanel panel_option_7 = new JPanel();
			
			panel_option_7.setLayout(null);
			panel_option_7.setBackground(Color.WHITE);
			
			frame_option_7.setSize(1000,800);
			frame_option_7.setLayout(null);
			
			JLabel id_label = new JLabel("Member ID:");
			JTextField id_field = new JTextField();
			
			JLabel doi_label = new JLabel("DOI Number:");
			JTextField doi_field = new JTextField();
			
			JButton submit_button = new JButton("Submit");
			
			frame_option_7.add(panel_option_7);
			panel_option_7.setBounds(0,0,frame_option_7.getWidth(),frame_option_7.getHeight());
			
			panel_option_7.add(id_label);
			id_label.setBounds(20, 30, 120, 30);
			panel_option_7.add(id_field);
			id_field.setBounds(150, 30, 250, 30);
			
			panel_option_7.add(doi_label);
			doi_label.setBounds(20, 80, 120, 30);
			panel_option_7.add(doi_field);
			doi_field.setBounds(150, 80, 250, 30);
			
			panel_option_7.add(submit_button);
			submit_button.setBounds(200, 130, 100, 30);
			
			submit_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int id = Integer.parseInt(id_field.getText());
					String doi = doi_field.getText();
					
					if(id_field.getText().isEmpty() || doi.isEmpty()) {
						JOptionPane.showMessageDialog(frame_option_7, "Please fill all the fields"
								, "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					else {
						boolean is_article_found = false;
						OnlineArticle target_article = null;
						for (OnlineArticle article : OnlineArticle.articleArray) {
							if (doi.equals(article.DOI)) {
								is_article_found = true;
								target_article = article;
								break;
							}
						}
						boolean is_user_found = false;
						RegularMember target_member = null;
						for (RegularMember member : RegularMember.memberArray) {
							if (id == member.id) {
								is_user_found = true;
								target_member = member;
								break;
							}
						}
						if (is_article_found && is_user_found) {
							target_member.returnOA(target_article.DOI);
							JOptionPane.showMessageDialog(null, "Online Article '"+
							target_article.nameOfArticle+"'"+ " (DOI:"+target_article.DOI+") returned by "+
									target_member.name);
						}
					}
				}
			});
			frame_option_7.setVisible(true);
		}
		else if (selected_value == menu_options[7] && e.getSource() == ok_button) {
			JFrame frame_option_8 = new JFrame("All Member Accounts");
			JPanel panel_option_8 = new JPanel();
			JTextArea text_area = new JTextArea();
			
			
			panel_option_8.setLayout(null);
			panel_option_8.setBackground(Color.WHITE);
			
			frame_option_8.setSize(1000,800);
			frame_option_8.add(panel_option_8);
			text_area.setBounds(20, 20, 900, 700);
			panel_option_8.add(text_area,BorderLayout.CENTER);
			
			text_area.setText("");
			
			for (RegularMember member : RegularMember.memberArray) {
		        text_area.append(member.toString());
		    }
			
			frame_option_8.setVisible(true);
		}
		else if (selected_value == menu_options[8] && e.getSource() == ok_button) {
			JFrame frame_option_9 = new JFrame("Library Database");
			JPanel panel_option_9 = new JPanel();
			JTextArea text_area = new JTextArea();
			JButton close  =new JButton("Close");
			
			panel_option_9.setLayout(new BorderLayout());
			
			frame_option_9.setSize(1000,800);
			frame_option_9.add(panel_option_9);
			panel_option_9.add(text_area,BorderLayout.CENTER);
			panel_option_9.add(close,BorderLayout.SOUTH);
			
			text_area.setText("");
			
			for(Book book : Book.bookArray) {
				text_area.append(book.toString());
			}
			
			frame_option_9.setVisible(true);
			frame_option_9.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			close.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					frame_option_9.dispose();
				}
			});
		}
		else if (selected_value == menu_options[9] && e.getSource() == ok_button) {
			JFrame frame_option_10 = new JFrame("Overdue Payments (Descending)");
			JPanel panel_option_10 = new JPanel();
			JTextArea text_area = new JTextArea();
			
			panel_option_10.setLayout(null);
			panel_option_10.setBackground(Color.WHITE);
			
			frame_option_10.setSize(1000,800);
			frame_option_10.add(panel_option_10);
			text_area.setBounds(20, 20, 900, 700);
			panel_option_10.add(text_area);
			
			text_area.setText("");
			
			Collections.sort(RegularMember.memberArray);
			
			for (RegularMember mem : RegularMember.memberArray) {
				text_area.append("User Name: "+mem.name+", ID: "+mem.id+", Cost: "+mem.calculateCost()+"\n");
			}

			frame_option_10.setVisible(true);
		}
		else if (selected_value == menu_options[10] && e.getSource() == ok_button) {
			System.exit(0);
		}
	}
}
