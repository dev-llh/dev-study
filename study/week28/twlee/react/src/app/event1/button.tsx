'use client'
export default function Button() {
  function handleClick() {
    alert('clicked')
  }

  return (
    <button onClick={handleClick}>
      click me
    </button>
  )
}