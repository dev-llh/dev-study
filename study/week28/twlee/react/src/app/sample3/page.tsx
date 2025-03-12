import MyButton from "../components/my-button";

export default function Page() {
  return (
    <div>
      <p>Hello child component</p>
      <MyButton
        text={"hello props"}
        style={{ bgColor: "#d3d3d3", size: "50px" }}
      />
    </div>
  );
}