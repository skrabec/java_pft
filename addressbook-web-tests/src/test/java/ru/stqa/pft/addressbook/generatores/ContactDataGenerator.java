package ru.stqa.pft.addressbook.generatores;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import java.util.Base64;


public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (
            ParameterException e) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("json")) {
      saveAsJSON(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private List<ContactData> generateContacts(int count) throws IOException {
    List<ContactData> contacts = new ArrayList<ContactData>();
    //byte[] fileContent = FileUtils.readFileToByteArray(new File("src/test/resources/stru.png"));
    //String encodedString = Base64.getEncoder().encodeToString(fileContent);
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstName(String.format("firstname %s", i)).withLastName(String.format("lastname %s", i)).withMiddleName(String.format("middlename %s", i))
              .withGroup("[none]").withNickName(String.format("nickname %s", i)).withTitle(String.format("title %s", i))
              .withCompany(String.format("company %s", i)).withAddress(String.format("address %s", i)).withEmail(String.format("test%s@mail.com", i)).withMobile(String.format("%s", i))
              .withHomePhone(String.format("%s", i)).withWorkPhone(String.format("%s", i)).withEmail(String.format("test%s@mail.com", i)).withEmail(String.format("test%s@mail21.com", i)));
    }
    return contacts;
  }
}