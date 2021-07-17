package DAO;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import databaseClasses.appointment;
import databaseClasses.candidates;
import databaseClasses.complains;
import databaseClasses.doctor;
import databaseClasses.customer;
import databaseClasses.jobs;
import databaseClasses.medicine;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

public class DB {
    // create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(customer.class)
                                                                .addAnnotatedClass(doctor.class)
                                                                .addAnnotatedClass(medicine.class)
                                                                .addAnnotatedClass(complains.class)
                                                                .addAnnotatedClass(jobs.class)
                                                                .addAnnotatedClass(candidates.class)
                                                                .addAnnotatedClass(appointment.class)
								.buildSessionFactory();
		
		// create session
                   Session session=factory.openSession();
                public String saveCustomer(String name,int age,String email,String pass,String mobile,String address,String city,
                        String state,Date date, String gender)  {
                    try{
		 //session = factory.getCurrentSession();
                    customer cust=new customer();
                    cust.setName(name);
                    cust.setAge(age);
                    cust.setEmail(email);
                    cust.setPassword(pass);
                    cust.setMobile(mobile);
                    cust.setAddress(address);
                    cust.setCity(city);
                    cust.setState(state);
                    cust.setDob(date);
                    cust.setGender(gender);
                    session.beginTransaction();
                    session.save(cust);
                    session.getTransaction().commit();
                    System.out.println("Done");
                    return "Done";
                    }catch(ConstraintViolationException  e){
                        System.out.println("Saving problem");
                        e.printStackTrace();
                        return "exception";
                    }
                    catch(Exception e){
                        System.out.println("No duplicate problemmmmmmmmmm");
                        return "Done";
                    }
                }
                
                
                public customer customerLogin(String user, String pass){
                    String hql="from customer where email = :user and password = :pass";
                    Query query=session.createQuery(hql);
                    query.setParameter("user", user);
                    query.setParameter("pass", pass);
                    List<customer> list=query.list();
                    if(list.size()==0)
                        return null;
                    else{
                    customer cust=list.get(0);
                        return cust;
                    }
                    
                }
                
                public String EmployeeRegister(String name,int age,String email,String pass,String mobile,String address,String city,
                        String state,Date date, String gender,String aadhar,String pan,String field,String college){
                    try{
                        doctor doc=new doctor();
                        doc.setEmail(email);
                        doc.setName(name);
                        doc.setPassword(pass);
                        doc.setAge(age);
                        doc.setAadhar(aadhar);
                        doc.setAddress(address);
                        doc.setCity(city);
                        doc.setCollege(college);
                        doc.setDob(date);
                        doc.setGender(gender);
                        doc.setField(field);
                        doc.setPan(pan);
                        doc.setState(state);
                        doc.setMobile(mobile);
                    session.beginTransaction();
                    session.save(doc);
                    session.getTransaction().commit();
                    return "done";
                    }catch(ConstraintViolationException  e){
                        System.out.println("Saving employee problem");
                        e.printStackTrace();
                        return "exception";
                    }
                    catch(Exception e){
                        System.out.println("No duplicate problemmmmmmmmmm");
                        return "Done";
                    }
                }

                public doctor EmployeeLogin(String user, String pass){
                    String hql="from doctor where email = :user and password = :pass";
                    Query query=session.createQuery(hql);
                    query.setParameter("user", user);
                    query.setParameter("pass", pass);
                    List<doctor> list=query.list();
                    if(list.size()==0)
                        return null;
                    else{
                        doctor dc=list.get(0);
                        return dc;
                    }
                }

                public String enterMedicine(String name,int quantity,String price,Date mfg,Date exp,String prescription,String url){
                    try{
                    medicine mc=(medicine)session.get(medicine.class,name);
                    if(mc==null){
                        mc=new medicine();
                        mc.setExp(exp);
                        mc.setMfg(mfg);
                        mc.setName(name);
                        mc.setPrescription(prescription);
                        mc.setPrice(price);
                        mc.setQuantity(quantity);
                        mc.setURL(url);
                        session.beginTransaction();
                    session.save(mc);
                    session.getTransaction().commit();
                    return "done";
                    }
                    else{
                        System.out.println("already present in db");
                       String hql="update medicine set exp = :exp , mfg = :mfg , Quantity = :quantity where name = :name";
                    Query query=session.createQuery(hql);
                    query.setParameter("exp", exp);
                    query.setParameter("mfg", mfg);
                    query.setParameter("quantity", mc.getQuantity()+quantity);
                    query.setParameter("name", name); 
                    int ct=query.executeUpdate();
                    session.beginTransaction();
                    session.getTransaction().commit();
                    if(ct!=0)
                        return "done";
                    return "no";
                    }
                    }catch(Exception e){
                        System.out.println("entering medicine problem");
                        e.printStackTrace();
                        return "exception";
                    }
                }
                
                public List<medicine> getMedicine(){
                    String hql="from medicine";
                    Query query=session.createQuery(hql);
                    List<medicine> list=query.list();
                    return list;
                }
                
                public HashMap<String,Double> getPrice(){
                    String hql="from medicine";
                    Query query=session.createQuery(hql);
                    List<medicine> list=query.list();
                    HashMap<String,Double> map=new HashMap<>();
                    int i=0;
                    while(i<list.size()){
                        medicine med=list.get(i++);
                        double d=Double.parseDouble(med.getPrice().substring(2));
                        map.put(med.getName(),d);
                    }
                    return map;
                }
                
                public String saveComplain(String name,int age,String description,String email,String mobile,String gender){
                    complains com=new complains();
                    try{
                    com.setAge(age);
                    com.setDescription(description);
                    com.setEmail(email);
                    com.setName(name);
                    com.setGender(gender);
                    com.setMobile(mobile);
                    session.beginTransaction();
                    session.save(com);
                    session.getTransaction().commit();
                    return "done";
                    }catch(Exception e){
                        System.out.println("saving complain problem");
                    e.printStackTrace();
                    return "exception";
                    }
                }
                
                public List<complains> getComplains(){
                    String hql="from complains";
                    Query query=session.createQuery(hql);
                    List<complains> list=query.list();
                    return list;
                }
                
                public String deleteObject(String id,String serv){
                    try{
                    if(serv.equalsIgnoreCase("comp")){
                        int Id=Integer.parseInt(id);
                    session.beginTransaction();
                        complains com=(complains)session.get(complains.class, Id);
                        if(com==null)
                            return "none";
                        session.delete(com);
                    session.getTransaction().commit();
                    }
                    else if(serv.equalsIgnoreCase("med")){
                        session.beginTransaction();
                        medicine med=(medicine)session.get(medicine.class, id);
                        if(med==null)
                            return "none";
                        session.delete(med);
                    session.getTransaction().commit();
                    }
                    else if(serv.equalsIgnoreCase("emp")){
                        session.beginTransaction();
                        doctor doc=(doctor)session.get(doctor.class, id);
                        if(doc==null)
                            return "none";
                        session.delete(doc);
                    session.getTransaction().commit();
                    }
                    else if(serv.equalsIgnoreCase("job")){
                        int Id=Integer.parseInt(id);
                        session.beginTransaction();
                        jobs jb=(jobs)session.get(jobs.class, Id);
                        if(jb==null)
                            return "none";
                        session.delete(jb);
                    session.getTransaction().commit();
                    }
                    return null;
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("DB delete problem");
                        return "exception";
                    }
                }
                
                public List forMail(String user){
                   String hql="from customer where email = :user"; 
                  try{ 
                      Query query=session.createQuery(hql);
                    query.setParameter("user", user);
                    List<customer> list=query.list();
                    if(list.size()==0){
                    System.out.println("ooooooooooooooooooooooooooooooooooooo0");
                        return null;
                    }
                    return list;
                  }catch(Exception e){
                      e.printStackTrace();
                      System.out.println("for mail problem  iii");
                      return null;
                  }
                  
                }
                
                public String saveJob(String position,int experience,int salary,String descrip){
                    try{
                        jobs jb=new jobs();
                        jb.setDescription(descrip);
                        jb.setExperience(experience);
                        jb.setPosition(position);
                        jb.setSalary(salary);
                        session.beginTransaction();
                        session.save(jb);
                        session.getTransaction().commit();
                        return "done";
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("saving job problemmmm");
                        return "exception";
                    }
                }
                
                public List<jobs> showJobs(){
                    try{
                    String hql="from jobs";
                    Query query=session.createQuery(hql);
                    List<jobs> list=query.list();
                    return list;    
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("showing jobs problem");
                        return null;
                    }
                }
                
                public String saveCanidate(int id,String fname,String lname,String city,String state,String gender,String address,
                        int exp,byte[] b,String mobile,String email){
                    try{
                        candidates can=new candidates();
                        can.setAddress(address);
                        can.setCity(city);
                        can.setEmail(email);
                        can.setExperience(exp);
                        can.setFirst_name(fname);
                        can.setGender(gender);
                        can.setLast_name(lname);
                        can.setMobile(mobile);
                        can.setState(state);
                        can.setResume(b);
                        
                        jobs jb=(jobs)session.get(jobs.class, id);
                        jb.add(can);
                        
                        session.beginTransaction();
                        session.save(can);
                        session.getTransaction().commit();
                        return "done";
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("saving candidate problem");
                        return "exception";
                    }
                }
                
                public List<doctor> getDoctor(){
                   String hql="from doctor where field = :user";
                   try{
                      Query query=session.createQuery(hql);
                    query.setParameter("user", "Doctor");
                       List<doctor> list=query.list();
                       return list;
                   }catch(Exception e){
                       e.printStackTrace();
                       System.out.println("gettong doctors problem");
                       return null;
                   }
                    
                }
                
             
                public HashMap<String,Object> appoint(String description,Date dt,String time,String customerId){
                    List<doctor> list=getDoctor();
                    //System.out.println(list);
                    try{
                        HashMap<String,Object> map=new HashMap<>();
                        map.put("time", time);
                        map.put("date",dt);
                        map.put("docName",null);
                    String hql="from appointment where doctorId =:doc and Date=:dt and Time=:time"; //and 
                    Query query=session.createQuery(hql);
                    for(int i=0;i<list.size();i++){
                        doctor temp=list.get(i);
                        query.setParameter("doc",temp.getEmail());
                        query.setParameter("dt",dt);
                        query.setParameter("time",time);
                        List<appointment> list2=query.list();
                        //System.out.println(list2);
                        
                        if(list2.size()==0){
                            appointment ap=new appointment();
                            ap.setCustomerId(customerId);
                            ap.setDate(dt);
                            ap.setTime(time);
                            ap.setDescription(description);
                            doctor doct=(doctor)session.get(doctor.class, temp.getEmail());
                            doct.add(ap);
                            session.beginTransaction();
                            session.save(ap);
                            session.getTransaction().commit();
                            map.put("docName", temp.getName());
                            break;
                        }
                        
                        
                    }
                    return map;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                        System.out.println("appointment database problem");
                        return null;
                    }
                }
                
                public List<appointment> getSchedule(String doctorId,Date dt){
                    try{
                        String hql="from appointment where doctorId =:doc and Date=:dt";
                        Query query=session.createQuery(hql);
                        query.setParameter("doc",doctorId);
                        query.setParameter("dt",dt);
                        List<appointment> list2=query.list();
                        return list2;
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("schedule problem");
                    }
                    return null;
                }
                
                public customer getCustomer(String email){
                    try{
                        String hql="from customer where email =:email ";
                        Query query=session.createQuery(hql);
                        query.setParameter("email",email);
                        List<customer> list2=query.list();
                        return list2.get(0);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                        System.out.println("getting Single customer problem");
                    }
                    return null;
                }
}
