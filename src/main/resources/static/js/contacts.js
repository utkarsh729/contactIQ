console.log("contacts.js")
// options with default values

const viewContactModal=document.getElementById('view_contact_modal');
// const baseURL = "http://localhost:8080";
//const baseURL = "http://contactiq.ap-south-1.elasticbeanstalk.com";
const baseURL = "https://www.contactiq.site";
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
  id: 'view_contact_modal',
  override: true
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal(){
    contactModal.show();
}

function closeContactModal(){
    contactModal.hide();
}

async function loadContactData(id){
    console.log(id); 

    try{
        const data = await (
            await fetch(`${baseURL}/api/contact/${id}`)
        ).json();
        // 2 await for handling 2 promise one from json and other from fetch
        console.log(data);

        document.querySelector("#contact_name").innerHTML = data.name;
        document.querySelector("#contact_email").innerHTML = data.email;
        document.querySelector("#contact_image").src = data.picture || '/images/user.png';
        document.querySelector("#contact_address").innerHTML = data.address || "-";
        document.querySelector("#contact_fav").innerHTML = data.favourite;
        document.querySelector("#contact_description").innerHTML = data.description || "-";
        // document.querySelector("#contact_instaLink").href = data.instaLink;
        // document.querySelector("#contact_linkedInLink").href = data.linkedInLink;

    //    document.querySelector("#contact_birthday").textContent = data.birthday
    //        ? new Date(data.birthday).toISOString().split('T')[0]
    //        : "-";

        document.querySelector("#contact_birthday").textContent = data.birthday
        ? new Date(data.birthday).toLocaleDateString('en-GB', { day: '2-digit', month: '2-digit', year: 'numeric' })
        : "-";





        document.querySelector("#contact_instaLink a").href = data.instaLink;
        document.querySelector("#contact_linkedInLink a").href = data.linkedInLink;
        document.querySelector("#contact_instaLink a").textContent = data.instaLink;
        document.querySelector("#contact_linkedInLink a").textContent = data.linkedInLink;

        const favouriteElement = document.querySelector("#contact_fav");
        if (data.favourite) {
            favouriteElement.innerHTML = `<i class="fa-solid fa-star text-yellow-500"></i>`; // Yellow star for favourite
        } else {
            favouriteElement.innerHTML = `<i class="fa-solid fa-xmark text-red-500"></i>`; // Red cross for not favourite
        }

      

        openContactModal();
    }
    catch(error){
        console.log(error);
    }


    
}
async function deleteContact(id){
    Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
      }).then((result) => {
        if (result.isConfirmed) {
          const url = `${baseURL}/user/contacts/delete/` + id;
          window.location.replace(url);

          Swal.fire({
            title: "Deleted!",
            text: "Your contact has been deleted.",
            icon: "success"
          });
        }
      });
}

function openEmailModal(email) {
    const emailInput = document.getElementById('recipientEmail');
    if (emailInput) {
        emailInput.value = email;
    }
    if (emailModal) {
        emailModal.classList.remove('hidden');
    }
}

function closeEmailModal() {
    if (emailModal) {
        emailModal.classList.add('hidden');
    }
}

document.getElementById('emailForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const formData = new FormData();
    formData.append('to', document.getElementById('recipientEmail').value);
    formData.append('subject', document.getElementById('subject').value);
    formData.append('message', document.getElementById('message').value);
    
    const attachment = document.getElementById('attachment').files[0];
    if (attachment) {
        formData.append('attachment', attachment);
    }

    const token = document.querySelector('meta[name="_csrf"]')?.getAttribute('content');
    const header = document.querySelector('meta[name="_csrf_header"]')?.getAttribute('content');

    headers: token && header ? { [header]: token } : {}

    try {
        const response = await fetch('/user/contacts/sendEmail', {
            method: 'POST',
            body: formData,
            headers: token && header ? { [header]: token } : {}
        });

        if (response.ok) {
            Swal.fire({
                title: 'Success!',
                text: 'Email sent successfully',
                icon: 'success'
            });
            closeEmailModal();
        } else {
            const err = await response.text();
            throw new Error(err || 'Failed to send email');
        }
    } catch (error) {
        Swal.fire({
            title: 'Error!',
            text: error.message || 'Failed to send email',
            icon: 'error'
        });
    }
});


