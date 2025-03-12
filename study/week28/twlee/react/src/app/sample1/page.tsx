export default function Page() {
  const src = '/sample.png'
  const desc = 'sample'
  return (
    <img
      className="avatar"
      src={src}
      alt={desc}
    />
  );
}