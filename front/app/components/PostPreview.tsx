import { DUMMY_POSTS, PostType } from "@/constants/dummy";
import Image from "next/image";
import PostPreviewCard from "./PostPrevieCard";

export default function PostPreview() {
  return (
    <div className='grid grid-cols-1 gap-2 sm:grid-cols-2'>
      {DUMMY_POSTS.map((post) => (
        <PostPreviewCard
          comments={post.comments}
          createdAt={post.createdAt}
          updatedAt={post.updatedAt}
          address={post.address}
          author={post.address}
          id={post.id}
          key={post.id}
          image={post.image}
          title={post.title}
          contents={post.contents}
        />
      ))}
    </div>
  );
}
